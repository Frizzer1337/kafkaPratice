# kafkaPratice
Проект банковской системы/Banking system project

### Стек технологий/Used technologies:<br />
* Java 17<br />
* Kafka(reactor),<br />
* Reactor,<br />
* MongoDB(reactivestreams),<br />
* Docker(for Kafka),<br />
* Spring boot,<br />
* Insomnia(from API requests)<br />


# Структура проекта<br />
#### 1)Borrower microservice<br />
Позволяет регистрировать заемщиков, брать кредиты, отправляет события в кафку о взятии кредита<br />
<br />
#### 2)Approve microservice<br />
При взятии кредита получает об этом топик в кафке, подтверждает, отклоняет или отправляет кредит на проверку работнику банка<br />
(считает коэффицент одобрения основываясь на личных данных заемщика),<br />
отправляет об этом соотвествующее сообщение в кафку<br />
<br />
#### 3)Payment microservice<br />
Принимает платежи от заемщиков, при просрочке платежа начисляет пени,<br />
при большом пени отправляет его коллекторам,<br />
при оплате всего кредита изменяет его статус<br />
<br />
#### 4)Collector microservice<br />
Позволяет регистрировать коллекторов, брать им кредиты для работы по выбиванию долгов<br />
Обзванивает всех заемщиков, у которых просрочен кредит раз в день<br />
<br />
#### 5)Notification microservice<br />
Отправляет пользователю нотификации о всех изменениях связанных с его кредитом:<br />
Отправке на проверку, результате проверки, платежах, просрочке платежа, отправке кредита коллекторам<br />
<br />
#### 6)Kafka api<br />
Содержит все сущности проекта, для удобного доступа между модулями<br />
<br />
# Project structure<br />
#### 1)Borrower microservice<br />
Allows to register borrowers, take credits, send kafka topic when credit is taken<br />
<br />
#### 2)Approve microservice<br />
Handles kafka topic with new credit, approves, declines or send credit to bank worker, send result to kafka<br />
(credit approve/decline mechanism work based on borrower personal information)<br />
<br />
#### 3)Payment microservice<br />
Accept payments from borrowers, if payment is delayed accures penalty,<br />
when penalty is big send credit to collectors,<br />
when credit payed off changes it status<br />
<br />
#### 4)Collector microservice<br />
Allows to register collectors, take credits into work ,<br />
calls borrowers every day<br />
<br />
#### 5)Notification microservice<br />
Send borrower notification about all changes connected with his credits:<br />
Credit creation, approve/decline, payments, credit overdue, send credit to collectors<br />
<br />
#### 6)Kafka api<br />
Contains all entities of project for convenient use<br />
