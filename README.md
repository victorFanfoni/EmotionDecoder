# EmotionDecoder

## Nome dos Integrantes

- Victor Fanfoni RM-99173
- Helena Paixão RM-550929
- Gustavo Costa RM-99102
- Julia Nery RM-552292
- Giulia Pina RM-97694

## Sobre o Projeto

**EmotionDecoder** é um aplicativo Android desenvolvido para ajudar os usuários a entender e interpretar suas emoções. Adotando o padrão arquitetônico MVC (Model-View-Controller), o projeto busca garantir uma estrutura de código bem organizada e uma experiência de usuário fluida, facilitando a manutenção e a evolução do aplicativo.

### Funcionalidades

- **Tela de Criação de Conta**: Permite aos novos usuários criar uma conta com validação de dados e integração com Firebase.
- **Tela de Triagem Emocional**: Coleta e analisa as emoções dos usuários através de questionários e ferramentas de análise, oferecendo insights valiosos.
- **Tela de Login**: Fornece uma experiência de login segura e eficiente, incluindo uma opção para recuperação de senha.
- **Tela de Redefinição de Senha**: Permite aos usuários redefinir suas senhas em caso de esquecimento, facilitando o acesso à conta.
- **Tela de Resultados**: Exibe uma análise detalhada das emoções dos usuários com base nas respostas fornecidas.

### Arquitetura do Projeto

O projeto é organizado da seguinte forma:

- **Controller**: Gerencia a lógica de controle e a interação entre os modelos e as vistas, assegurando que a aplicação funcione de forma coesa.
  - `CreateAccountController`
  - `EmotionalScreeningController`
  - `LoginController`
  - `ResetPasswordController`
  - `ResultScreenController`

- **Model**: Representa os dados e a lógica de negócio, facilitando o gerenciamento e a manipulação das informações.
  - `Auth`
  - `CreateAccountModel`
  - `EmotionModel`
  - `FirebaseAuthProvider`
  - `ResultScreenModel`
  - `UserModel`

- **View**: Cuida da interface do usuário e da apresentação das informações, proporcionando uma experiência visualmente agradável.
  - `CreateAccount`
  - `EmotionalScreening`
  - `Login`
  - `ResetPasswordActivity`
  - `ResultScreen`

- **MainActivity**: A classe principal que inicializa o aplicativo e gerencia seu ciclo de vida.

### Tecnologias Utilizadas

- **Android Studio**: IDE utilizada para o desenvolvimento Android.
- **Kotlin**: Linguagem moderna usada para criar o EmotionDecoder.
- **Firebase**: Plataforma para gerenciamento de autenticação e dados de usuários.

### Instalação e Execução

1. Clone o repositório:
   ```bash
   git clone <URL do repositório>
