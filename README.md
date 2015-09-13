Busca de CEP
==================================
Implementação de busca de CEPs usando banco de dados em memória (H2). Essa aplicação foi desenvolvida com fins de teste.

Este aplicativo foi desenvolvido usando spring-boot para facilitar o uso da aplicação tanto de forma standalone quanto com deploy em servidores de aplicação.

O desenvolvimento foi realizado baseado nas técnicas de TDD e DDD e com foco em simplicidade.

##Sobre os componentes usados
Para construção dessa aplicação, as seguintes tecnologias foram usadas:

- Spring-boot;
- Spring-MVC;
- Spring-data-jpa;
- Hibernate 4;
- JPA 2.1;
- Java 8;
- H2;
- Maven.

##Utilização em produção
Esse aplicativo foi desenvolvido apenas para fins de teste, mas pode ser facilmente expandido para uso em produção a partir do uso de perfis no Spring ou no próprio maven. 

##Utilização
Por default a aplicação é inicializada na porta **8080**.

Você pode checar se a aplicação está rodando, acessando a url:
http://localhost:8080

a aplicação deverá exibir na tela a mensagem: "Up and running!"

###Para pesquisar um CEP
Exemplo de url para pesquisa de :
http://localhost:8080/addresses/01001-000 

é um exemplo de CEP válido. O retorno será:

    {
		"cep": "01001001",
		"street": "Praça da Sé - lado par",
		"neighborhood": "Sé",
		"city": "São Paulo",
		"state": "SP"
	}

Caso o CEP pesquisado seja inválido, será retornado o status 412 (Pré-condition failed)
junto ao conteúdo:

	{
		"errorMessage": "CEP inválido"
	}

**Observação:**
Como a aplicação foi desenvolvida com fins de teste, apenas os CEPs do município de São Paulo estão disponíveis.