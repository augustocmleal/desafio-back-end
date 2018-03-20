
# Informações e instruções de configuração da API.

Esta API Rest Foi desenvolvida utilizando Java com Spring-boot, MyBatis, JSoup, Postgresql e o parse do Xml via JAXB

OBS: Você deve ter o Postgresql na máquina para iniciar...

## 1. Crie um usuário no Posgresql: 

nome: globo 

senha: 201803


## 2. Crie o banco no Postgresql:
```
--Criação do banco
CREATE DATABASE desafiobackend
  WITH OWNER = globo;

--Criação do schema core
CREATE SCHEMA core
  AUTHORIZATION globo;

-- Criação da tabela user
CREATE TABLE core.user
(
  id smallserial NOT NULL,
  name character varying(30) NOT NULL,
  password character varying(30) NOT NULL,
  CONSTRAINT pk_core_user PRIMARY KEY (id),
  CONSTRAINT uk_1_core_user UNIQUE (name)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE core.user
  OWNER TO globo;
```

## 3. Insira o usuário mock: 
Ele está autorizado a utilizar a api
```
INSERT INTO core.user (name, password) VALUES ('globo', '219D99E1DC8E8647398EF8515FD66E51');
```
## 4. Clone o projeto num diretório local

## 5. Execute o projeto:
Ele foi feito com spring-boot, basta rodar App.java e nossa API estará no ar.

Você também pode executar a chamada via Junit 

Ele está como defaut na porta 8383 mas no arquivo application.yaml você pode alterar antes de executá-lo.



# NOTAS IMPORTANTES:
Esta é uma API Rest, ela responde a requisições através de url e parâmetros específicos. 
Nesta versão está configurada apenas a url "/auto-esporte/feed" que trará o feed atualizado baseado no Xml disponível em "https://revistaautoesporte.globo.com/rss/ultimas/feed.xml"

## A API espera um "Authentication" no Header para verificar o usuário baseado no Http Basic.
Use este pois é o que foi gerado a partir do usuário autorizado:
```
"Authorization" : "Basic Z2xvYm86MjAxODAz"
```


## Exemplo de requisição:

```
var settings = {
  "async": true,
  "crossDomain": true,
  "url": "http://localhost:8383/auto-esporte/feed",
  "method": "GET",
  "headers": {
    "authentication": "Basic Z2xvYm86MjAxODAz",
    "cache-control": "no-cache",
    "postman-token": "c0968862-e6da-71ea-b5ad-c87f5cfd7cb8"
  }
}

$.ajax(settings).done(function (response) {
  console.log(response);
});
```
