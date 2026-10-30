# Chinese Municipalities (Practical Five)

An Android Studio Java/XML application that accepts Beijing, Shanghai, Tianjin, or Chongqing and opens a themed profile with a city image, a short factual overview, and a button to the relevant official municipal website.

## Open and run

Open this folder (`PracticalFive`) in Android Studio, allow Gradle sync to finish, then run the `app` configuration on an emulator or Android device. The app uses the included Gradle wrapper and needs Android SDK Platform 37 (or update `compileSdk` to a platform installed on your machine).

Enter a municipality name and press Go. Letter case does not matter. Unsupported or blank input stays on the first screen and shows a validation error.

## Project structure

- `MainActivity` validates the form with `TextProcessor` and passes the normalised city using an explicit Intent.
- `Municipality` maps each city to its array entry, image, named colours, and official website; the Visit site button uses an implicit `ACTION_VIEW` Intent.
- `res/values/arrays.xml` stores summaries in Beijing, Shanghai, Chongqing, Tianjin order.
- The supplied images and app logo are in `res/drawable`, with adaptive and legacy launcher icons in `res/mipmap-*`.

## Sources for the summaries and official-site links

City descriptions were written in original wording using municipal government background pages and UNESCO references for the named heritage facts:

- Beijing Municipal Government (English): https://english.beijing.gov.cn/
- Shanghai Municipal Government (English): https://english.shanghai.gov.cn/
- Tianjin Municipal Government (English): https://en.tj.gov.cn/
- Chongqing Municipal Government (English): https://english.cq.gov.cn/
- UNESCO World Heritage, Beijing Central Axis: https://whc.unesco.org/en/list/1714/
- UNESCO World Heritage, Dazu Rock Carvings: https://whc.unesco.org/en/list/912/
