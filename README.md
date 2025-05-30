# Sistema de Cadastro e Notificação de Eventos

O programa foi desenvolvido originalmente para um exercício de faculdade, mas foram feitas melhorias para deixar o sistema mais completo e funcional. O objetivo é criar uma solução em Java para cadastrar, consultar e gerenciar eventos que acontecem na cidade do usuário, usando programação orientada a objetos.



---
**Descrição do Projeto**

Este sistema automatiza o cadastro e notificação de eventos locais, facilitando que usuários participem de festas, esportes, shows e outras atividades.

**Funcionalidades principais**

- Cadastro de usuários com atributos como nome, email e telefone  
- Cadastro de eventos com nome, endereço, categoria, horário e descrição  
- Consulta de eventos para confirmar ou cancelar presença  
- Visualização dos eventos confirmados pelo usuário  
- Ordenação dos eventos por horário, mostrando quais estão acontecendo ou já passaram  
- Salvamento e carregamento dos eventos em arquivo texto (`events.data`)

**Tecnologias utilizadas**

- Java (console)  
- Orientação a objetos  
- Git para controle de versão  
- Manipulação de datas e horários com classes DateTime

**Arquitetura**

O projeto segue o paradigma orientado a objetos, organizado para facilitar manutenção e melhorias. Não foi obrigatório usar MVC, mas a separação de responsabilidades foi adotada para clareza no código.

**Como utilizar**

1. Clone ou baixe o repositório  
2. Abra em sua IDE preferida (Eclipse, IntelliJ, NetBeans, Replit etc)  
3. Compile e execute o programa  
4. Use o console para cadastrar usuários, eventos e gerenciar participações  
5. Os eventos cadastrados são carregados automaticamente do arquivo `events.data` ao iniciar
