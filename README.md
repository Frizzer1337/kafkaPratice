# kafkaPratice
Проект банковской системы/Banking system project

### Стек технологий/Used technologies:__
Java 17,__ 
Kafka(reactor),__
Reactor,__
MongoDB(reactivestreams),__
Docker(for Kafka),__
Spring boot,__ 
Insomnia(from API requests)__


Структура проекта__
1)Borrower microservice__
Позволяет регистрировать заемщиков, брать кредиты, отправляет события в кафку о взятии кредита__
2)Approve microservice__
При взятии кредита получает об этом топик в кафке, подтверждает, отклоняет или отправляет кредит на проверку работнику банка__
(считает коэффицент одобрения основываясь на личных данных заемщика),__ 
отправляет об этом соотвествующее сообщение в кафку__
3)Payment microservice__
Принимает платежи от заемщиков, при просрочке платежа начисляет пени,__ 
при большом пени отправляет его коллекторам,__ 
при оплате всего кредита изменяет его статус__
4)Collector microservice__
Позволяет регистрировать коллекторов, брать им кредиты для работы по выбиванию долгов__
Обзванивает всех заемщиков, у которых просрочен кредит раз в день__
5)Notification microservice__
Отправляет пользователю нотификации о всех изменениях связанных с его кредитом:__
Отправке на проверку, результате проверки, платежах, просрочке платежа, отправке кредита коллекторам__
6)Kafka api__
Содержит все сущности проекта, для удобного доступа между модулями__

Project structure__
1)Borrower microservice__
Allows to register borrowers, take credits, send kafka topic when credit is taken__
2)Approve microservice__
Handles kafka topic with new credit, approves, declines or send credit to bank worker, send result to kafka__
(credit approve/decline mechanism work based on borrower personal information)__
3)Payment microservice__
Accept payments from borrowers, if payment is delayed accures penalty,__
when penalty is big send credit to collectors,__
when credit payed off changes it status__
4)Collector microservice__
Allows to register collectors, take credits into work ,__
calls borrowers every day__
5)Notification microservice__
Send borrower notification about all changes connected with his credits:__
Credit creation, approve/decline, payments, credit overdue, send credit to collectors__
6)Kafka api__
Contains all entities of project for convenient use__
