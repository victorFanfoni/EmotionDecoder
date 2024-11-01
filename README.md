# EmotionDecoder

## Índice

- [Integrantes](#integrantes)
- [Descrição do Projeto](#descrição-do-projeto)
- [Funcionalidades](#funcionalidades)
- [Arquitetura do Projeto](#arquitetura-do-projeto)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Instalação e Execução](#instalação-e-execução)
- [Contribuições](#contribuições)
- [Licença](#licença)

## Integrantes

- **Victor Fanfoni** (RM-99173)
- **Helena Paixão** (RM-550929)
- **Gustavo Costa** (RM-99102)
- **Julia Nery** (RM-552292)
- **Giulia Pina** (RM-97694)

## Descrição do Projeto

**EmotionDecoder** é um aplicativo Android inovador desenvolvido para auxiliar os usuários na compreensão e interpretação de suas emoções. Utilizando o padrão arquitetônico MVC (Model-View-Controller), este projeto assegura uma estrutura de código organizada e uma experiência de usuário intuitiva, facilitando a manutenção e a evolução contínua do aplicativo.

## Funcionalidades

- **Criação de Conta**: Permite que novos usuários criem contas com validação de dados e integração com o Firebase.
- **Triagem Emocional**: Coleta e analisa as emoções dos usuários por meio de questionários e ferramentas de análise, fornecendo insights valiosos.
- **Login Seguro**: Proporciona uma experiência de login eficiente, incluindo opções para recuperação de senha.
- **Redefinição de Senha**: Facilita a redefinição de senhas em caso de esquecimento, garantindo o acesso à conta.
- **Resultados Detalhados**: Apresenta uma análise abrangente das emoções dos usuários, baseada em suas respostas.
- **Atualização de Cadastro**: Permite que os usuários atualizem suas informações pessoais de forma simples e eficaz.
- **Tela Inicial (Home)**: Oferece um panorama das funcionalidades do aplicativo, facilitando a navegação.
- **Atualização de Usuário**: Habilita os usuários a modificarem suas informações de perfil de maneira intuitiva.

## Arquitetura do Projeto


O projeto é estruturado da seguinte maneira:

- **Controllers**: Gerenciam a lógica de controle e a interação entre os modelos e as views, assegurando uma operação coesa da aplicação.
   - `CreateAccountController`
   - `EmotionalScreeningController`
   - `LoginController`
   - `ResetPasswordController`
   - `ResultScreenController`
   - `UpdateController`

- **Models**: Representam os dados e a lógica de negócios, facilitando a gestão e manipulação das informações.
   - `Auth`
   - `CreateAccountModel`
   - `EmotionModel`
   - `FirebaseAuthProvider`
   - `ResultScreenModel`
   - `UserModel`

- **Views**: Responsáveis pela interface do usuário e apresentação das informações, garantindo uma experiência visualmente agradável.
   - `CreateAccount`
   - `EmotionalScreening`
   - `Login`
   - `ResetPasswordActivity`
   - `ResultScreen`
   - `Home`
   - `UpdateActivity`

- **MainActivity**: Classe principal que inicializa o aplicativo e gerencia seu ciclo de vida.

## Tecnologias Utilizadas

- **Android Studio**: Ambiente de desenvolvimento integrado utilizado para o desenvolvimento Android.
- **Kotlin**: Linguagem moderna empregada para a construção do EmotionDecoder.
- **Firebase**: Plataforma para gerenciamento de autenticação e dados dos usuários.

## Instalação e Execução

Para configurar e executar o projeto, siga os passos abaixo:

1. Clone o repositório:
   ```bash
   git clone https://github.com/victorFanfoni/EmotionDecoder.git
