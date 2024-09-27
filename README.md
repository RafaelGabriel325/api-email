# api-email
api-email
Esta API é uma solução desenvolvida em Spring Boot para o envio de e-mails, permitindo o envio tanto para um único destinatário quanto em lote. É ideal para aplicações que precisam integrar funcionalidades de envio de e-mails, como notificações, confirmações e mensagens de marketing. A API utiliza o Mailtrap para testes de envio de e-mail, proporcionando um ambiente seguro e controlado para desenvolvimento.

Funcionalidades Enviar E-mail para um Único Destinatário:

Endpoint: POST /api/email/enviar-email Descrição: Permite o envio de um e-mail para um único destinatário. O corpo da requisição deve conter um JSON, XML ou YML que representa o e-mail, incluindo campos como destinatário, assunto e conteúdo. Respostas: 200 OK: O e-mail foi enviado com sucesso. 400 Bad Request: A requisição não é válida (ex.: dados ausentes ou formato incorreto). 401 Unauthorized: Falha de autenticação. 500 Internal Server Error: Erro no servidor durante o processamento. Enviar E-mails em Lote:

Endpoint: POST /api/email/enviar-email-lote Descrição: Permite o envio de múltiplos e-mails em uma única requisição. O corpo da requisição deve conter um array de JSON, XML ou YML representando cada e-mail a ser enviado. Respostas: 200 OK: Os e-mails foram enviados com sucesso. 400 Bad Request: A requisição não é válida. 401 Unauthorized: Falha de autenticação. 500 Internal Server Error: Erro no servidor durante o processamento.

Configuração do Banco de Dados e E-mail A API é configurada para utilizar o PostgreSQL como banco de dados, e as configurações necessárias são definidas no arquivo application.yml. Além disso, a API utiliza o Mailtrap (https://mailtrap.io/) como servidor SMTP para envio de e-mails, permitindo testes sem o risco de enviar e-mails para destinatários reais.

Testando a API Para testar a API, você pode usar ferramentas como Postman ou cURL para enviar requisições para os endpoints. Certifique-se de que o servidor Mailtrap está configurado corretamente e que as credenciais no arquivo application.yml são válidas. Você poderá verificar os e-mails enviados na interface do Mailtrap, onde todos os e-mails são capturados sem serem enviados para os destinatários reais.
