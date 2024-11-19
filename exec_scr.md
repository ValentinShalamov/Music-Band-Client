
# Как работать с `execute_script`
Команда `execute_script` поддерживает все команды с интерактивного режима.

**Этапы работы:**

Подготовьте файл для скрипта - пропишите в файле последовательно на отдельных строках нужные команды
```bash

  help

  info

  show
```
Пробелы перед, до, после и между командами игнорируются.

Команды ниже принимают один аргумент сразу после имени команды

- `filter <количество продаж>`

- `remove <id>`

- `remove_lower <количество продаж>`

Пример текста скрипта для выполнения этих команд

```bash
  filter 777

  remove 3

  remove_lower 100000
```

Для команд с большим количеством аргументов - `add`, `add_if_min`, `update <id>` - необходимо передавать аргументы в следующих строках, в том же порядке, что и в интерактивном режиме

```bash
add
The Beatles
4
brit_pop
Hey Jude
87654321

add_if_min
Кино
5
math_rock
Группа крови
100000000

update 28
Nautilus Pompilius
10
math_rock
Князь тишины
777777777
```

**Вызов команды**

Во время работы с программой в интерактивном режиме введите `execute_script`

```bash
  Enter a command:
  execute_script
```

Введите путь к файлу-скрипту (путь может быть любым)

```bash
  Enter the path to the file:
  /home/valentin/Java_Projects/MusicBand/MusicBandClient/script
```

Скрипт будет выполнен только в том случае, если не было ни единой синтаксической ошибки в файле, иначе выведутся в консоль те строки, которые не удовлетворяют нормальной работе скрипта

```bash
  Command: show_mineв is incorrect command
  Command: filtear 201 is incorrect command
  Command: add_if_min Error message: The number of participants can be from 1 to 1000 
```

## Связанные проекты
[Сервер](https://github.com/ValentinShalamov/Music-Band-Server)

[Клиент](https://github.com/ValentinShalamov/Music-Band-Client)

## Данные для связи

Email для связи: valentin98shalamov@gmail.com
