# Boris Kotliarov

**Javarush University module 1 project:**
***CryptoAnalyzer***

**16.03.2022 update**
 - Added statistical analysis. **CAN handle BIG txt files**. RU text only.
 - Cesar regular Encrypt, decrypt via key and brute force working with RU text only. **CANNOT handle big txt files** cuz of JAVA FX TextArea appending problem.

 - New Alphabet:
   RU: {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'й' ,'к', 'л', 'м', 'н', 'о', 'п', 
   'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'я', 'А', 'Б',
   'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т',
   'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', '.', ',', '«', '»',
   '"', '\'', ':', '!', '?', ' '};

 - Brute Force working with valid **russian** (english incoming) text where's the **"space" character is more common than every other character.**
 
#TODO

 - Handle big files with encrypt, decrypt via key, brute force.
 - Implement manual character swapping.
 - Analysis file save;
 - Handle English text.
