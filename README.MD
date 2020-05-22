# SNL编译器
## 简介
一个SNL语言的编译器，采用JAVA 12 语言实现，IDE为IDEA，测试采用Junit单元测试。
## GUI
提供一个简单的GUI界面
## 词法分析
1. 手写正则表达式引擎，采用NFA实现，可用？、*、+、[a-z]、（）、|、.语法
## 语法分析
1. 采用LL1语法分析
2. 从文本中读取产生式，产生式的规范化
3. 计算first集 follow集和predict集
3. 记录保存分析过程
4. 生成语法树
## 4.示例程序
```
program p
type
    t = integer;
    a=array[3..5] of integer;
    vv=record 
        integer v3,cd;
     end;
var
    t v1;
    char v2;
    a v3;
{
测试注释
}
procedure test1(integer in1,in2);
begin
    in1:=in2
end

begin
    if i<2 then 
        v2:=3
    else
        v2:=4
    fi;
    while i<3 do
        v2:=v2+1
    endwh;
    v2:='1';
    v2:=vv.cd;
    read(v1);
    v1:=v1+10;
    write(v1)
end
```