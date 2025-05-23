#language: ru

@catalog_block
Функционал: Блок категории курсов Otus

  Предыстория: Выбор категории курса
    Пусть Открыта главная страница в браузере

  Структура сценария: Выбор категории курса
    Когда Выбрать категорию <Курс>
    Тогда Откроется страница Каталог
    И Чекбокс номер <Номер Чекбокса> будет Отмечен

    Примеры:
      | Курс             | Номер Чекбокса |
      | Программирование | 2              |
      | Архитектура      | 3              |