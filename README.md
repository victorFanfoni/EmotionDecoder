# EmotionDecoder

## Nome dos integrantes:
Victor Fanfoni RM-99173
Helena Paixão RM-550929
Gustavo Costa RM-99102
Julia Nery RM-552292
Giulia Pina RM-97694
## Explanação do Projeto

**EmotionDecoder** é um aplicativo Android desenvolvido com o objetivo de analisar e interpretar emoções dos usuários. O aplicativo adota o padrão MVC (Model-View-Controller) para uma clara separação de responsabilidades e uma arquitetura organizada. 

### Funcionalidades

- **Tela de Criação de Conta**: Permite ao usuário criar uma nova conta com validação de entrada e integração com Firebase.
- **Tela de Triagem Emocional**: Coleta e analisa as emoções do usuário usando questionários e ferramentas de análise.
- **Tela de Login**: Fornece opções de autenticação para acesso ao aplicativo.
- **Tela de Redefinição de Senha**: Permite ao usuário redefinir sua senha em caso de esquecimento.
- **Tela de Resultados**: Exibe os resultados da análise emocional com base nas respostas fornecidas.

### Arquitetura do Projeto

O projeto é estruturado da seguinte forma:

- **Controller**: Contém classes responsáveis pela lógica de controle e interação entre o modelo e a visão.
  - `CreateAccountController`
  - `EmotionalScreeningController`
  - `LoginController`
  - `ResetPasswordController`
  - `ResultScreenController`

- **Model**: Contém as classes de modelo que representam os dados e a lógica de negócio.
  - `Auth`
  - `CreateAccountModel`
  - `EmotionModel`
  - `FirebaseAuthProvider`
  - `ResultScreenModel`
  - `UserModel`

- **View**: Contém as classes de visão que gerenciam a interface do usuário e a apresentação das telas.
  - `CreateAccount`
  - `EmotionalScreening`
  - `Login`
  - `ResetPasswordActivity`
  - `ResultScreen`

- **MainActivity**: Classe principal que inicia o aplicativo.

### Tecnologias Utilizadas

- **Android Studio**: Ambiente de desenvolvimento integrado para Android.
- **Kotlin**: Linguagem de programação utilizada para o desenvolvimento do aplicativo.
- **Firebase**: Plataforma para autenticação e gerenciamento de usuários.

### Instalação e Execução

1. Clone o repositório: `git clone <URL do repositório>`
2. Abra o projeto no Android Studio.
3. Configure o Firebase conforme as instruções em [Firebase Setup](https://firebase.google.com/docs/android/setup).
4. Compile e execute o aplicativo no emulador ou dispositivo Android.

Para mais informações, consulte a documentação do projeto ou entre em contato com o desenvolvedor.
