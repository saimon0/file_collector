URUCHOMIENIE:

Program został napisany przy pomocy środowiska do budowania Maven 

Aby uruchomić program najlepiej wykorzystać IDE IntelliJ Idea.

Po otwarciu katalogu programu przy użyciu IntelliJ Idea, należy przejść do pliku src/main/java/pretius_task.java i uruchomić ten plik.

Po uruchomieniu, program utworzy 3 katalogi: HOME, DEV, TEST.
W katalogu HOME będzie znajdować się plik count.txt z wartościami początkowymi.

Podczas próby utworzenia pliku może pojawić się komunikat z błędem IDE ("Cannot Create File, Cannot create child file"), jednak plik zostanie utworzony i od razu przeniesiony do właściwego katalogu.

Po utworzeniu pliku .jar lub .xml w katalogu HOME program automatycznie przeniesie zadany plik do właściwego katalogu tj. TEST lub DEV zgodnie z ustalonymi kryteriami.

Kryteria przeniesienia do katalogu:
- DEV (jeśli plik ma rozszerzenie .jar i godzina utworzenia jest parzysta, jeśli plik ma rozszerzenie xml)
- TEST (jeśli plik ma rozszerzenie .jar i godzina utworzenia jest nieparzysta)

Po przeniesieniu, nastąpi aktualizacja pliku count.txt.

IDE może pokazywać dane tj. lokalizacje plików lub stan pliku count.txt z opóźnieniem. Po każdej operacji przeniesienia, utworzenia pliku w katalogu HOME najlepiej wykonać odświeżenie (File -> Reload All from Disk lub Ctrl + Alt + Y).

Aby zatrzymać program należy wpisać "stop" w konsoli programu.

Działanie programu dla nieprzystej godziny utworzenia pliku .jar można przetestować poprzez np. zmianę godziny na systemie operacyjnym.


