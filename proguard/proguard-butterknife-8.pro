# ButterKnife 8

# Retain generated class which implement ViewBinder.
-keep public class * implements butterknife.internal.ViewBinder {
    public <init>();
}

# Prevent obfuscation of types which use ButterKnife annotations since the simple name
# is used to reflectively look up the generated ViewBinder.
-keep class butterknife.*
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}