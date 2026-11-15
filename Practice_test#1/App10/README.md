# Catalog de cărți

Aplicație Android în **Java**, cu interfață definită în **XML**, realizată ca exercițiu pentru testul de la disciplina Dezvoltarea Aplicațiilor Mobile. Aplicația permite adăugarea, editarea și ștergerea cărților dintr-un catalog.

## Funcționalități

- **Catalog** cu 10 cărți generate la pornire, afișate într-un `ListView` și ordonate descrescător după preț.
- **Adăugare** printr-un formular cu titlu, preț, număr de pagini și categorie. Categoria se alege dintr-un `Spinner` cu opțiunile Roman, Tehnica și Istorie.
- **Editare** la apăsarea pe o carte. Formularul se deschide cu valorile existente, iar lista se reordonează după salvare.
- **Ștergere** la apăsare lungă, cu confirmare într-un `AlertDialog`. Dacă utilizatorul refuză, cartea rămâne în listă și detaliile ei apar într-un `Toast`.
- **Validare** a datelor din formular, cu mesaje de eroare pe ecran și în Logcat.
- **Ecran de categorii** cu trei butoane generate dinamic într-un `LinearLayout`. La apăsare, fiecare afișează numele categoriei într-un `Toast`.
- **Cod de acces local** salvat între porniri cu `SharedPreferences`.

## Structura aplicației

| Clasă | Rol |
| --- | --- |
| `Carte` | Modelul de date. Implementează `Serializable` pentru transferul prin `Intent`. |
| `MainActivity` | Codul de acces, lista de cărți, sortarea și confirmarea ștergerii. |
| `MainActivity2` | Formularul comun pentru adăugare și editare, cu `Spinner` și validare. |
| `MainActivity3` | Generarea butoanelor pentru categorii. |

## Rulare

1. Clonează repository-ul sau descarcă proiectul.
2. Deschide în Android Studio folderul care conține `settings.gradle.kts`.
3. Așteaptă sincronizarea Gradle.
4. Selectează un emulator sau un telefon Android și apasă **Run**.

Codul de acces se păstrează între porniri. Catalogul este ținut în memorie și se inițializează din nou la pornirea aplicației.