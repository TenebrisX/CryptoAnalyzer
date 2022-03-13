# Boris Kotliarov

**Javarush University module 1 project:**
***CryptoAnalyzer***

**13.03.2022 20:30 update**
 - added encryption, decryption via key, brute force decryption.
 - Statistic analysis incoming.

 - The Alphabet: {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и','к',
   'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь',
   'э', 'я', 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'К', 'Л', 'М', 'Н', 'О', 'П',
   'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', 'a', 'b',
   'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
   'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
   'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1','2','3','4','5',
   '6','7','8','9','0','.', ',', '«', '»', '\'', '"', ':', '!', '?', ' ', '—', '-', '*', '/',
   '\\', '+', '=', ';', '(',')','[',']','{','}','~','`','#','$','%','^','&','№','_', '|'}

 - Brute Force working only with valid russian/english text where the "space" character is more common than every other character.
   Cannot decode nonsense or way to short sentences without key.
   The logic is to find the "space" character.