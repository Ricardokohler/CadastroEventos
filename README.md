Sistema de Cadastro e Notificação de Eventos
O programa foi desenvolvido originalmente para um exercício de faculdade, mas foram criadas melhorias visando tornar o sistema mais completo e funcional. O objetivo principal é oferecer uma solução em Java para o cadastro, consulta e gerenciamento de eventos que ocorrem na cidade do usuário, utilizando conceitos de programação orientada a objetos e boas práticas de desenvolvimento.

Descrição do Projeto
Este sistema foi pensado para atender a necessidade de automatizar e digitalizar o processo de cadastro e notificação de eventos locais, facilitando a participação dos usuários em atividades culturais, esportivas, shows e outras categorias de eventos.

Funcionalidades Principais
Cadastro de usuários com atributos completos (nome, email, telefone, entre outros).

Cadastro de eventos, que incluem nome, endereço, categoria, horário e descrição.

Categorias pré-definidas para os eventos, como festas, esportes, shows e outras.

Consulta de eventos cadastrados com possibilidade de confirmação ou cancelamento da participação.

Visualização dos eventos em que o usuário confirmou presença.

Ordenação dos eventos por horário, destacando os eventos que estão ocorrendo no momento e os que já passaram.

Persistência dos eventos em arquivo de texto (events.data), que é carregado automaticamente ao iniciar o programa.

Tecnologias Utilizadas
Linguagem: Java

Ambiente de Desenvolvimento: Console (linha de comando)

Estrutura: Orientação a Objetos

Controle de versão: Git (repositório disponível)

Manipulação de datas e horários via classes DateTime do Java

Arquitetura
O projeto foi estruturado seguindo o paradigma orientado a objetos, com organização modular para facilitar manutenção e futuras melhorias. Embora o uso do padrão MVC não tenha sido obrigatório, a arquitetura incentiva a separação de responsabilidades para maior clareza do código.

Como Utilizar
Clone ou faça o download do repositório.

Abra o projeto em sua IDE preferida (Eclipse, IntelliJ, NetBeans, Replit, etc).

Compile e execute o programa.

Siga as instruções no console para cadastrar usuários, eventos, confirmar presença, consultar eventos e salvar suas informações.

Ao iniciar o programa, os eventos previamente cadastrados são carregados automaticamente do arquivo events.data.
