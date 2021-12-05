# Teste Meli Simio

Projeto para teste vaga meli - Hygor Martins

<p align="center"><img width=70% src="https://s2.glbimg.com/Bu6upvmSg6SRv0za635uXphThKo=/620x430/e.glbimg.com/og/ed/f/original/2020/03/28/mercado-livre.jpg"></p>

## Features

#### Registrar novo DNA
**api link:** <a hreg="https://api-simios-hygor.herokuapp.com/simian">https://api-simios-hygor.herokuapp.com/simian</a>

**Exemplo de request Post para Postman:**
```
curl --location --request POST 'https://api-simios-hygor.herokuapp.com/simian' \
--header 'Content-Type: application/json' \
--data-raw '{
  "dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]
}'
```
**response:**
<p>Caso seja um simian: HTTP 200-OK</p>
<p>Caso seja um human: HTTP 403-FORBIDDEN</p>

#### DNA stats
**api link:** <a hreg="https://api-simios-hygor.herokuapp.com/stats">https://api-simios-hygor.herokuapp.com/stats</a>

**Exemplo de request Get para Postman:**
```
curl --location --request GET 'https://api-simios-hygor.herokuapp.com/stats'
```
**response example:**
```
{
    "count_human_dna": 1,
    "count_mutant_dna": 6,
    "ratio": 6.0
}
```
## Tecologias
<div style="display: inline_block"><br>
  <img align="center" alt="Java" height="110" width="120" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg">
  <img align="center" alt="Spring" height="110" width="120" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original-wordmark.svg">
  <img align="center" alt="Mongo" height="110" width="120" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mongodb/mongodb-original-wordmark.svg">
  <img align="center" alt="Heroku" height="110" width="120" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/heroku/heroku-plain-wordmark.svg">
  <br>
</div>