### HW7:

> Работа должна быть оформлена следующим образом:
>- каждая домашняя работа должна быть оформлена как отдельный **PULL REQUEST** в репозитории.
>- в корневой папке проекта должен находиться файл readme.md, который будет содержать описание сервиса, инструкции по запуску,
   ссылка на главный класс, скриншоты критических этапов работы
>- ресурсы по заданиям (скриншоты, диаграммы и т.п.) должны располагаться в подпапках ’resources’ папок с выполненными заданиями

#### Задание:
> - Клонировать репозитории ваших коллег по проекту, запустить и убедиться, что они корректно отрабатывают, основываясь на readme.md.
> - С помощью WebClient написать механизм получения данных от  внешних сервисов (сервисы ваших коллег), endpoint - /discovery.
    > Вышеописанный механизм должен предоставлять информацию о внешних сервисах и реализовывать интерфейс ServiceDiscovery, содежащий метод JsonString discoverService(String url)
```
    public interface ServiceDiscovery {
        JsonString discoverService(url);
    }
```

> Пример отправляемого запроса и ответа:
```bash 
curl -X GET host:port/api/discovery
```

Response body:
```json
   {
       "teammate1_service_name": {
         ответ, полученный из эндопоинта version если service1/liveness is up. Иначе сообщение "teammate1_service_name is not available"
       },
      "teammate2_service_name": {
        ответ, полученный из эндопоинта version если service2/liveness is up. Иначе сообщение "teammate2_service_name is not available"
      }
   }
``` 
teammate1_service_name, teammate2_service_name - наименования сервисов ваших коллег по проекту
> - Написать тесты для своего микросервисного приложения, используя библиотеку WebTestClient и Mock.

#### Задание со звездочкой:
> При написании тестов придерживаться структуры Given-When-Then.

### Ресурсы для самостоятельного ознакомления
- [WebClient](https://www.baeldung.com/spring-5-webclient)
- [JUnit Documentation](https://junit.org/junit5/docs/current/user-guide/)
- [JUnit by Baeldung](https://www.baeldung.com/junit-5)
- [Mockito by Baeldung](https://www.baeldung.com/mockito-series)