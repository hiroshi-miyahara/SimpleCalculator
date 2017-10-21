@del *.class 2> nul

javac Calc.java
@if ERRORLEVEL 1 goto err_exit1

jar cvfm Calc.jar Calc.mf *.class jp/zousoft/calc/*.class

:err_exit1
@del *.class jp\zousoft\calc\*.class 2> nul

javac RPC.java
@if ERRORLEVEL 1 goto err_exit2

jar cvfm RPC.jar RPC.mf *.class jp/zousoft/calc/*.class jp/zousoft/rpc/*.class

:err_exit2
@del *.class jp\zousoft\calc\*.class jp\zousoft\rpc\*.class 2> nul
