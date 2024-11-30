
# Music Band Client

## Введение

**Music Band Client** - консольное приложение для взаимодействия с сервером, предназначенное для управления коллекцией объектов `MusicBands` в интерактивном режиме. 

В данном **readme** описывается только `клиентская` часть приложения. С `серверной` частью (в т.ч. техническими особенностями) вы можете ознакомиться, перейдя по ссылке [Server](https://github.com/ValentinShalamov/Music-Band-Server?tab=readme-ov-file)

## Начало работы

Для запуска клиента необходима версия `Java 17` или выше.

Загрузите клиент с репозитория:

```bash
  git clone https://github.com/ValentinShalamov/Music-Band-Client.git
```

Перейдите в корневую папку с проектом:

```bash
  cd MusicBandClient
```

Предоставьте доступ на выполнение скриптам `clean.sh`, `build.sh` и `run.sh`:
```bash
  chmod u+x *.sh 
```

Запустите приложение с помощью команды:

```bash
  ./run.sh
```

## Особенности реализации
- Сетевое взаимодействие с сервером осуществляется по протоколу `TCP`
- Обмен данными с сервером осуществляется в формате `json` в `блокирующем режиме`
- Чтение и ожидание команд от пользователю осуществляется в интерактивном консольном режиме
- Реализована валидация данных, введенных пользователями
- Возможность регистрации и авторизации пользователей
- Чтение паролей осуществляется с отключенным эхом
- Поддержка выполнения команд из файла через команду `execute_script`
  
## Список команд

Клиент предоставляет консольный интерфейс для отправки команд серверу. Ниже представлен перечень поддерживаемых команд:

- #### Регистрация и авторизация
- `login` - авторизация пользователя
- `register` - регистрация пользователя
- #### Справка и информация:
- `help` - вывести список доступных команд
- `info` - вывести информацию о коллекции
- `history` - вывести последние 12 команд
- `history_clear` - очистить историю команд
- #### Фильтрация и поиск
- `show` - отобразить все музыкальные группы
- `show_mine` - вывести только свои музыкальные группы
- `show_min` - отобразить группы с минимальным числом продаж лучшего альбома
- `print_asc` - вывести группы в порядке возрастания
- `print_desc` - вывести группы в порядке убывания
- `filter <количество продаж>` - найти группы с указанным числом продаж лучшего альбома
- #### Изменение данных
- `add` - добавить новую музыкальную группу
- `add_if_min` - добавить группу, если продажи ее лучшего альбома меньше, чем у всех существующих групп пользователя
- `update <id>` - обновить данные своей группы по указанному `id`
- #### Удаление данных
- `remove <id>` - удалить свою группу по указанному `id`
- `remove_lower <количество продаж>` - удалить все свои группы с меньшим числом продаж
- `clear` - удалить все свои группы
- #### Дополнительные функции
- `execute_script` - выполнить команды из файла
[подробная инструкция](https://github.com/ValentinShalamov/Music-Band-Client/blob/master/exec_scr.md)
- `exit` - завершить работу приложения
- `q` - отмена текущего ввода (доступно в любой момент)

## Данные для связи
Email для связи: valentin98shalamov@gmail.com
