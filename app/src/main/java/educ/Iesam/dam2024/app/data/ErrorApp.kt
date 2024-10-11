package educ.Iesam.dam2024.app.data

sealed class ErrorApp {

    //Clase para manejar los errores de la app
    //Es una sealedclass porque solo tiene un valo
    //Vamos a cecirle que tipo de ErrorApp tenemos, como un enum
    //Vamos a tratarlos como errores y no como excepciones. Predeciendo errores.
    //Hacer muchos try catch puede ser un mal diseño.
    //--------------------------------------------/

    object NetworkErrorApp : ErrorApp() //Automaticamente esta haciendo singleton
    object ServerErrorApp : ErrorApp()
    object DataErrorApp : ErrorApp()
    object UnknownErrorApp : ErrorApp()

}