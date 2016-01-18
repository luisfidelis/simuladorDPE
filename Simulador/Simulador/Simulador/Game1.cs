using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Audio;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.GamerServices;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using Microsoft.Xna.Framework.Media;
using Xls = Microsoft.Office.Interop.Excel;

namespace Simulador
{
    /// <summary>
    /// This is the main type for your game
    /// </summary>
    public class Game1 : Microsoft.Xna.Framework.Game
    {
        //Graficos
        GraphicsDeviceManager graphics;
        SpriteBatch spriteBatch;
        Texture2D background;
        Texture2D bola;
        SpriteFont abcd;

        //Output
        Xls.Application xlsApp;
        Xls.Workbook xlsWorkbook;
        Xls.Worksheet xlsWorksheet;

        //Dados a serem preenchidos
        const double massa_foguete_kilogramas = 0.1;
        const double aceleracao_gravidade = 9.81;
        double angulo_velocidade_inicial_rad = MathHelper.ToRadians(0);
        const double x_inicial_metros = 200;
        const double densidade_do_ar_kg_por_m3 = 1.2922;
        const double corda_media_da_asa_metro = 0.025;
        const double comprimento_da_asa_metro = 0.05;
        const double delta_tempo_segundos = 0.01;
        const double coeficiente_drag_fuselagem = 0.5;
        const double Impulso_motor_newton_segundo=15; //Impulso classe D. Altitude recorde ~500m
        const double tempo_queima_segundos =3;

        //Dados a serem entregues pelo "Programa embarcado"
        double coeficiente_lift = 0;
        double coeficiente_drag_asa = 0.007;
        double coeficiente_lift_max = 1.61;
        double coeficiente_drag_max=0.024;
        double coeficiente_lift_min=-0.2;
        double coeficiente_drag_min=0.007;

        //Dados a serem calculados
        double altitude_inical_metros;
        double velocidade_inicial_metros_por_segundo;
        double forca_drag_induzida_newtons =0;
        double forca_drag_parasita_newtons=0;
        double velocidade_inicial_x_metros_por_segundo;
        double velocidade_inicial_y_metros_por_segundo;

        //Dados a serem descobertos
        //double tempo_segundos;
        //double altitude_final;

        //Dados usados no calculo
        double velocidade_x_k_metros_por_segundo;
        double velocidade_y_k_metros_por_segundo;
        double velocidade_k_metros_por_segundo;
        double angulo_k_rad;
        double altitude_k_metros;
        double x_k_metros;
        double forca_lift_k_newtons;
        double forca_drag_k_newtons;
        double forca_drag_fuselagem_k_newtons;
        double forca_resultante_k_newtons;
        double angulo_forca_resultante_k_rad;
        
        int contador = 0;

        public Game1()
        {
            graphics = new GraphicsDeviceManager(this);
            Content.RootDirectory = "Content";
        }

        /// <summary>
        /// Allows the game to perform any initialization it needs to before starting to run.
        /// This is where it can query for any required services and load any non-graphic
        /// related content.  Calling base.Initialize will enumerate through any components
        /// and initialize them as well.
        /// </summary>
        protected override void Initialize()
        {
            // TODO: Add your initialization logic here
            xlsApp = new Xls.Application();
            xlsApp.Visible = false;
            xlsWorkbook = xlsApp.Workbooks.Open(@"C:\Users\lucas.milagres\Desktop\Teste de trajetoria.xlsx");
            xlsWorksheet =(Xls.Worksheet)xlsWorkbook.Sheets[1];
            xlsWorksheet.Cells[1, 1] = "Tempo(s)";
            xlsWorksheet.Cells[1, 2] = "X(m)";
            xlsWorksheet.Cells[1, 3] = "Y(m)";
            xlsWorksheet.Cells[1, 4] = "Cs(m)";
            xlsWorksheet.Cells[1, 5] = "Ci(m)";

            double forca_motor = Impulso_motor_newton_segundo / tempo_queima_segundos;
            altitude_inical_metros = ((forca_motor / massa_foguete_kilogramas) * Math.Pow(tempo_queima_segundos, 2)) / 2;
            velocidade_inicial_metros_por_segundo = (forca_motor / massa_foguete_kilogramas) * tempo_queima_segundos;
            velocidade_inicial_x_metros_por_segundo = velocidade_inicial_metros_por_segundo * Math.Cos(angulo_velocidade_inicial_rad);
            velocidade_inicial_y_metros_por_segundo = velocidade_inicial_metros_por_segundo * Math.Sin(angulo_velocidade_inicial_rad);

            altitude_k_metros = altitude_inical_metros;
            x_k_metros = x_inicial_metros;
            velocidade_x_k_metros_por_segundo = velocidade_inicial_x_metros_por_segundo;
            velocidade_y_k_metros_por_segundo = velocidade_inicial_y_metros_por_segundo;
            velocidade_k_metros_por_segundo = velocidade_inicial_metros_por_segundo;

            base.Initialize();
        }

        /// <summary>
        /// LoadContent will be called once per game and is the place to load
        /// all of your content.
        /// </summary>
        protected override void LoadContent()
        {
            // Create a new SpriteBatch, which can be used to draw textures.
            spriteBatch = new SpriteBatch(GraphicsDevice);
            background = Content.Load<Texture2D>("background");
            bola = Content.Load<Texture2D>("esquematico_foguete");
            abcd = Content.Load<SpriteFont>("abcd");
            graphics.PreferredBackBufferWidth = background.Width;
            graphics.PreferredBackBufferHeight = background.Height;
            graphics.IsFullScreen = false;
            IsMouseVisible = false;
            graphics.ApplyChanges();
            // TODO: use this.Content to load your game content here
        }

        /// <summary>
        /// UnloadContent will be called once per game and is the place to unload
        /// all content.
        /// </summary>
        protected override void UnloadContent()
        {
            // TODO: Unload any non ContentManager content here
        }

        /// <summary>
        /// Allows the game to run logic such as updating the world,
        /// checking for collisions, gathering input, and playing audio.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Update(GameTime gameTime)
        {
            // Allows the game to exit
            if (GamePad.GetState(PlayerIndex.One).Buttons.Back == ButtonState.Pressed)
                this.Exit();

            // TODO: Add your update logic here

            #region OutputExcel_rota_com_angulo_de_asa_padrao
            xlsWorksheet.Cells[contador + 2, 1] = contador * delta_tempo_segundos;
            xlsWorksheet.Cells[contador + 2, 2] = x_k_metros;
            xlsWorksheet.Cells[contador + 2, 3] = altitude_k_metros;
            #endregion

            #region Calculo_de_rota_com_angulo_de_asa_padrao
            angulo_k_rad = Math.Atan2(velocidade_y_k_metros_por_segundo, velocidade_x_k_metros_por_segundo);

            forca_lift_k_newtons = (densidade_do_ar_kg_por_m3 * coeficiente_lift * Math.Pow(velocidade_k_metros_por_segundo, 2) * corda_media_da_asa_metro * comprimento_da_asa_metro) / 2;
            forca_drag_fuselagem_k_newtons = (densidade_do_ar_kg_por_m3 * coeficiente_drag_fuselagem * Math.Pow(velocidade_k_metros_por_segundo, 2) * corda_media_da_asa_metro * comprimento_da_asa_metro) / 2;
            forca_drag_k_newtons = (densidade_do_ar_kg_por_m3 * coeficiente_drag_asa * Math.Pow(velocidade_k_metros_por_segundo, 2) * corda_media_da_asa_metro * comprimento_da_asa_metro) / 2 + forca_drag_fuselagem_k_newtons + forca_drag_induzida_newtons + forca_drag_parasita_newtons;
            forca_resultante_k_newtons = Math.Sqrt(Math.Pow(forca_lift_k_newtons * Math.Cos(angulo_k_rad+Math.PI/2) + forca_drag_k_newtons * Math.Cos(Math.PI + angulo_k_rad), 2) + Math.Pow(forca_lift_k_newtons*Math.Sin(Math.PI/2+angulo_k_rad) + forca_drag_k_newtons * Math.Sin(Math.PI + angulo_k_rad)-massa_foguete_kilogramas*aceleracao_gravidade, 2));
            angulo_forca_resultante_k_rad = Math.Atan2(forca_lift_k_newtons*Math.Sin(Math.PI/2+angulo_k_rad) + forca_drag_k_newtons * Math.Sin(Math.PI + angulo_k_rad) - massa_foguete_kilogramas * aceleracao_gravidade, forca_lift_k_newtons * Math.Cos(angulo_k_rad+Math.PI/2) + forca_drag_k_newtons * Math.Cos(Math.PI + angulo_k_rad));

            altitude_k_metros += velocidade_y_k_metros_por_segundo * delta_tempo_segundos;
            x_k_metros += velocidade_x_k_metros_por_segundo * delta_tempo_segundos;
            velocidade_x_k_metros_por_segundo += forca_resultante_k_newtons * Math.Cos(angulo_forca_resultante_k_rad) / massa_foguete_kilogramas * delta_tempo_segundos;
            velocidade_y_k_metros_por_segundo += forca_resultante_k_newtons * Math.Sin(angulo_forca_resultante_k_rad) / massa_foguete_kilogramas * delta_tempo_segundos;
            velocidade_k_metros_por_segundo = Math.Sqrt(Math.Pow(velocidade_x_k_metros_por_segundo, 2) + Math.Pow(velocidade_y_k_metros_por_segundo, 2));
            #endregion

            #region Identificador_de_Correção_Superior
            double angulo_k_temp = angulo_k_rad,
                   velocidade_x_k_temp = velocidade_x_k_metros_por_segundo,
                   velocidade_y_k_temp = velocidade_y_k_metros_por_segundo,
                   forca_lift_k_temp,
                   velocidade_k_temp = velocidade_k_metros_por_segundo,
                   forca_drag_k_temp,
                   forca_drag_fuselagem_k_temp,
                   forca_resultante_k_temp,
                   angulo_forca_resultante_k_temp,
                   forca_drag_induzida_temp = forca_drag_induzida_newtons,
                   forca_drag_parasita_temp = forca_drag_parasita_newtons,
                   altitude_k_temp = altitude_k_metros,
                   x_k_temp = x_k_metros;
                               
            do
            {
                angulo_k_temp = Math.Atan2(velocidade_y_k_temp, velocidade_x_k_temp);

                forca_lift_k_temp = (densidade_do_ar_kg_por_m3 * coeficiente_lift_max * Math.Pow(velocidade_k_temp, 2) * corda_media_da_asa_metro * comprimento_da_asa_metro) / 2;
                forca_drag_fuselagem_k_temp = (densidade_do_ar_kg_por_m3 * coeficiente_drag_fuselagem * Math.Pow(velocidade_k_temp, 2) * corda_media_da_asa_metro * comprimento_da_asa_metro) / 2;
                forca_drag_k_temp = (densidade_do_ar_kg_por_m3 * coeficiente_drag_max * Math.Pow(velocidade_k_temp, 2) * corda_media_da_asa_metro * comprimento_da_asa_metro) / 2 + forca_drag_fuselagem_k_temp + forca_drag_induzida_temp + forca_drag_parasita_temp;
                forca_resultante_k_temp = Math.Sqrt(Math.Pow(forca_lift_k_temp * Math.Cos(angulo_k_temp + Math.PI / 2) + forca_drag_k_temp * Math.Cos(Math.PI + angulo_k_temp), 2) + Math.Pow(forca_lift_k_temp * Math.Sin(Math.PI / 2 + angulo_k_temp) + forca_drag_k_temp * Math.Sin(Math.PI + angulo_k_temp) - massa_foguete_kilogramas * aceleracao_gravidade, 2));
                angulo_forca_resultante_k_temp = Math.Atan2(forca_lift_k_temp * Math.Sin(Math.PI / 2 + angulo_k_temp) + forca_drag_k_temp * Math.Sin(Math.PI + angulo_k_temp) - massa_foguete_kilogramas * aceleracao_gravidade, forca_lift_k_temp * Math.Cos(angulo_k_temp + Math.PI / 2) + forca_drag_k_temp * Math.Cos(Math.PI + angulo_k_temp));

                altitude_k_temp += velocidade_y_k_temp * delta_tempo_segundos;
                x_k_temp += velocidade_x_k_temp * delta_tempo_segundos;
                velocidade_x_k_temp += forca_resultante_k_temp * Math.Cos(angulo_forca_resultante_k_temp) / massa_foguete_kilogramas * delta_tempo_segundos;
                velocidade_y_k_temp += forca_resultante_k_temp * Math.Sin(angulo_forca_resultante_k_temp) / massa_foguete_kilogramas * delta_tempo_segundos;
                velocidade_k_temp = Math.Sqrt(Math.Pow(velocidade_x_k_temp, 2) + Math.Pow(velocidade_y_k_temp, 2));
            }
            while (velocidade_y_k_temp > 0);
            xlsWorksheet.Cells[contador + 2, 4] = altitude_k_temp;
            #endregion

            #region Identificador_de_Correção_Inferior
            angulo_k_temp = angulo_k_rad;
            velocidade_x_k_temp = velocidade_x_k_metros_por_segundo;
            velocidade_y_k_temp = velocidade_y_k_metros_por_segundo;
            velocidade_k_temp = velocidade_k_metros_por_segundo;
            forca_drag_induzida_temp = forca_drag_induzida_newtons;
            forca_drag_parasita_temp = forca_drag_parasita_newtons;
            altitude_k_temp = altitude_k_metros;
            x_k_temp = x_k_metros;

            do
            {
                angulo_k_temp = Math.Atan2(velocidade_y_k_temp, velocidade_x_k_temp);

                forca_lift_k_temp = (densidade_do_ar_kg_por_m3 * coeficiente_lift_min * Math.Pow(velocidade_k_temp, 2) * corda_media_da_asa_metro * comprimento_da_asa_metro) / 2;
                forca_drag_fuselagem_k_temp = (densidade_do_ar_kg_por_m3 * coeficiente_drag_fuselagem * Math.Pow(velocidade_k_temp, 2) * corda_media_da_asa_metro * comprimento_da_asa_metro) / 2;
                forca_drag_k_temp = (densidade_do_ar_kg_por_m3 * coeficiente_drag_min * Math.Pow(velocidade_k_temp, 2) * corda_media_da_asa_metro * comprimento_da_asa_metro) / 2 + forca_drag_fuselagem_k_temp + forca_drag_induzida_temp + forca_drag_parasita_temp;
                forca_resultante_k_temp = Math.Sqrt(Math.Pow(forca_lift_k_temp * Math.Cos(angulo_k_temp + Math.PI / 2) + forca_drag_k_temp * Math.Cos(Math.PI + angulo_k_temp), 2) + Math.Pow(forca_lift_k_temp * Math.Sin(Math.PI / 2 + angulo_k_temp) + forca_drag_k_temp * Math.Sin(Math.PI + angulo_k_temp) - massa_foguete_kilogramas * aceleracao_gravidade, 2));
                angulo_forca_resultante_k_temp = Math.Atan2(forca_lift_k_temp * Math.Sin(Math.PI / 2 + angulo_k_temp) + forca_drag_k_temp * Math.Sin(Math.PI + angulo_k_temp) - massa_foguete_kilogramas * aceleracao_gravidade, forca_lift_k_temp * Math.Cos(angulo_k_temp + Math.PI / 2) + forca_drag_k_temp * Math.Cos(Math.PI + angulo_k_temp));

                altitude_k_temp += velocidade_y_k_temp * delta_tempo_segundos;
                x_k_temp += velocidade_x_k_temp * delta_tempo_segundos;
                velocidade_x_k_temp += forca_resultante_k_temp * Math.Cos(angulo_forca_resultante_k_temp) / massa_foguete_kilogramas * delta_tempo_segundos;
                velocidade_y_k_temp += forca_resultante_k_temp * Math.Sin(angulo_forca_resultante_k_temp) / massa_foguete_kilogramas * delta_tempo_segundos;
                velocidade_k_temp = Math.Sqrt(Math.Pow(velocidade_x_k_temp, 2) + Math.Pow(velocidade_y_k_temp, 2));
            }
            while ((velocidade_x_k_temp > 0) || (velocidade_y_k_temp > 0));
            xlsWorksheet.Cells[contador + 2, 5] = altitude_k_temp;
            #endregion

            contador++;

            #region CloseExcel
            if (altitude_k_metros < -10)
            {
                xlsWorkbook.Save();
                xlsWorkbook.Close();
                xlsApp.Quit();
                this.Exit();
            }
            #endregion
            base.Update(gameTime);
        }

        /// <summary>
        /// This is called when the game should draw itself.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Draw(GameTime gameTime)
        {
            GraphicsDevice.Clear(Color.CornflowerBlue);

            // TODO: Add your drawing code here
            
            spriteBatch.Begin();
            spriteBatch.Draw(background, Vector2.Zero, Color.White);
            spriteBatch.Draw(bola, new Vector2((float)x_k_metros, (float)(background.Height - altitude_k_metros)), new Rectangle(0, 0, bola.Width, bola.Height), Color.White, -(float)angulo_k_rad, Vector2.Zero,1f, SpriteEffects.None, 0);
           /* spriteBatch.DrawString(abcd, "velocidade_k_metros_por_segundo: " + velocidade_k_metros_por_segundo.ToString(), new Vector2(20, 20), Color.Black);
            spriteBatch.DrawString(abcd, "angulo_k_rad: " + MathHelper.ToDegrees((float)angulo_k_rad).ToString(), new Vector2(20, 40), Color.Black);
            spriteBatch.DrawString(abcd, "forca_resultante_instantanea_newtons: " + forca_resultante_instantanea_newtons.ToString(), new Vector2(20, 60), Color.Black);
            */spriteBatch.DrawString(abcd, "altitude: " +altitude_k_metros.ToString(), new Vector2(20, 80), Color.Black);
            spriteBatch.End();
            base.Draw(gameTime);
        }
    }
}
