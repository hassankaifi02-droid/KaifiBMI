# Kaifi BMI (Android)

A simple offline Android BMI calculator. Enter weight in kilograms and height in centimetres.

## Build APK using only your Android phone
1. Create a new **public or private** GitHub repository named `KaifiBMI`.
2. Upload the complete contents of this folder to the repository (keep the folder structure). The easiest route is GitHub in Chrome: repository → Add file → Upload files.
3. Commit the files to the `main` branch.
4. Open the repository's **Actions** tab. The workflow named **Build Android APK** starts after the push. If it does not, select it and tap **Run workflow**.
5. Open the completed workflow run, scroll to **Artifacts**, and download `Kaifi-BMI-debug`.
6. Extract the ZIP on your phone, then tap `app-debug.apk` and allow installation from that source if Android asks.

## App details
- Offline; no account or internet permission required.
- Weight: kg. Height: cm.
- BMI = weight (kg) ÷ height (m)².
- Adult categories: underweight <18.5; healthy 18.5–24.9; overweight 25.0–29.9; obesity ≥30.
- BMI is a screening measure, not a medical diagnosis.

## Project
Native Kotlin Android app, minimum Android 6.0 (API 23), target SDK 35.
