# Practice Test #1 — Pregătire pentru testul practic Android

Material de recapitulare pentru testul practic la **Dezvoltarea Aplicațiilor Mobile**, bazat pe seminarele 1–6.

## Competențe urmărite

| Seminar | Ce trebuie să pot implementa |
| --- | --- |
| **1 — Interfață și navigare** | Configurez un `ImageView` și un `Switch`, schimb vizibilitatea controalelor în funcție de stare și deschid o activitate cu `Intent`. Trimit și citesc un text prin `putExtra()` și `getStringExtra()`. |
| **2 — Spinner și fragmente** | Populez un `Spinner` cu `ArrayAdapter`, citesc selecția și preselectez o valoare cu `setSelection()`. Dacă enunțul cere un fragment, îl includ în layout cu `FragmentContainerView` și configurez spinnerul în `onCreateView()`. |
| **3 — Layout-uri și butoane dinamice** | Poziționez controalele într-un `RelativeLayout` și le așez vertical sau orizontal într-un `LinearLayout`. Creez butoane în Java, le atașez un listener și le adaug în layout cu `addView()`. |
| **4 — Evenimente și confirmare** | Identific rândul apăsat într-un `ListView`. La apăsare lungă, deschid un `AlertDialog`. La confirmare, șterg rândul și actualizez lista. La refuz, afișez detaliile obiectului într-un `Toast`. Scriu mesaje de eroare în Logcat cu `Log.e()`. |
| **5 — Preferințe și dialoguri** | Citesc și salvez un cod de acces local cu `SharedPreferences`. Configurez un `AlertDialog` cu un câmp mascat și explic când se apelează `onCancel()` și `onDismiss()`. |
| **6 — Liste, formulare și rezultate** | Scriu o clasă cu patru atribute, doi constructori, `toString()` și `Serializable`. Afișez 10 obiecte într-un `ListView`, sortate descrescător după atributul cerut. Deschid un formular gol pentru adăugare și unul precompletat la click pe un rând. Validez datele, întorc obiectul cu `setResult()` și actualizez lista fără să pierd ordinea. |

## Verificare înainte de predare

- [ ] **Pornire.** Proiectul se deschide în Android Studio și afișează imaginea, butonul de adăugare și lista inițială cu 10 obiecte sortate.
- [ ] **Validare.** Testez câmpuri goale, valori care nu se pot converti și valori în afara intervalului. Formularul rămâne deschis, iar eroarea apare pe ecran și în Logcat.
- [ ] **Adăugare.** Salvez un obiect valid. Lista trece de la 10 la 11 rânduri, iar obiectul apare în poziția corectă.
- [ ] **Editare.** Deschid un rând la click simplu și verific toate valorile precompletate. Schimb atributul de sortare și confirm că rândul se mută, iar lista rămâne la 11 obiecte.
- [ ] **Ștergere.** La apăsare lungă, aleg „NU” și verific detaliile din `Toast`. Repet cu „DA” și verific că dispare doar obiectul selectat.
