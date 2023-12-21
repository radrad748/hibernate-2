1) таблица store колонка manager_staff_id убрал not null ,причина - в таблице store и в таблице staff ссылаються друг на друга
 по id и имеют модификатор not null, в таком случае мы не можем заргузить в базу даннных новую сущность либо store либо staff
 так как колонка foreign key будет null

2) Таблица customer колонка last_update стоит модификатор null , скорее всего должно быть not null
