# detekt

## Metrics

* 12 number of properties

* 5 number of functions

* 2 number of classes

* 2 number of packages

* 5 number of kt files

## Complexity Report

* 190 lines of code (loc)

* 152 source lines of code (sloc)

* 93 logical lines of code (lloc)

* 33 comment lines of code (cloc)

* 10 cyclomatic complexity (mcc)

* 5 cognitive complexity

* 4 number of total code smells

* 21% comment source ratio

* 107 mcc per 1,000 lloc

* 43 code smells per 1,000 lloc

## Findings (4)

### naming, FunctionMaxLength (1)

Function names should not be longer than the maximum set in the project configuration.

[Documentation](https://detekt.dev/docs/rules/naming#functionmaxlength)

* src/main/java/com/minafarid/advancedmultimodulararchitecture/ui/theme/Theme.kt:36:5
```
Function names should be at most 30 characters long.
```
```kotlin
33 )
34 
35 @Composable
36 fun AdvancedMultiModularArchitectureTheme(
!!     ^ error
37   darkTheme: Boolean = isSystemInDarkTheme(),
38   // Dynamic color is available on Android 12+
39   dynamicColor: Boolean = true,

```

### naming, FunctionNaming (3)

Function names should follow the naming convention set in the configuration.

[Documentation](https://detekt.dev/docs/rules/naming#functionnaming)

* src/main/java/com/minafarid/advancedmultimodulararchitecture/MainActivity.kt:40:5
```
Function names should match the pattern: ([a-z][a-zA-Z0-9]*)|(`.*`)
```
```kotlin
37 }
38 
39 @Composable
40 fun Greeting(modifier: Modifier = Modifier) {
!!     ^ error
41   Column(modifier = modifier) {
42     Text(
43       text = "Base Url: ${BuildConfig.BASE_URL}!",

```

* src/main/java/com/minafarid/advancedmultimodulararchitecture/MainActivity.kt:63:5
```
Function names should match the pattern: ([a-z][a-zA-Z0-9]*)|(`.*`)
```
```kotlin
60 
61 @Preview(showBackground = true)
62 @Composable
63 fun GreetingPreview() {
!!     ^ error
64   AdvancedMultiModularArchitectureTheme {
65     Greeting()
66   }

```

* src/main/java/com/minafarid/advancedmultimodulararchitecture/ui/theme/Theme.kt:36:5
```
Function names should match the pattern: ([a-z][a-zA-Z0-9]*)|(`.*`)
```
```kotlin
33 )
34 
35 @Composable
36 fun AdvancedMultiModularArchitectureTheme(
!!     ^ error
37   darkTheme: Boolean = isSystemInDarkTheme(),
38   // Dynamic color is available on Android 12+
39   dynamicColor: Boolean = true,

```

generated with [detekt version 1.23.3](https://detekt.dev/) on 2024-08-01 22:56:04 UTC
