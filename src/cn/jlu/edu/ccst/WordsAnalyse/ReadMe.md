# 词法分析器
## 1. 正则表达式引擎
参考资料
1. https://www.jianshu.com/p/52042b16843a?tdsourcetag=s_pcqq_aiomsg
2. https://segmentfault.com/a/1190000018258326
3. https://juejin.im/user/5b72ded8e51d45664c23a662/posts

## 2.已经完成
1. 正则表达式NFA引擎
    RegexUtil的两个函数可以直接使用。缺点：nfa每次都会初始化，浪费内存。
    建议多次使用同一正则表达式时创建一个nfa对象然后调用nfa对象那两个函数。
    如果还不会用就参考 Test.Util.RegexUtilTest 和 util.RegexUtil 。
2. 关于正则表达式的单元测试。

## 3.存在的问题
### 已解决
1. 词法分析后空格的token不要了，因为太多了，不方便查看和处理
2. 写的程序不用分号作为语句结束，分号是某些文法的
3. 保留字大写，最好大小写的关键字都能正常识别
4. record也是保留字
5. char '1'
6. 空行和注释行要算行数
7.windows换行为‘\r\n’ 解决方法：在调用端统一将代码的换行符换为'\n'   
```java
    String code;
    code=code.replace("\r","");
 ```
### 未解决
1. 直接以end结尾会被吞掉，end+空格结尾正常 ，end.报错，end.+空格正常
## 4.示例程序
```
program p
type
    t = integer;
    a=array[3..5] of integer;
    vv=record integer v3,cd end;
var
    t v1;
    char v2;
    a v3;
{
测试注释
}
procedure test1(integer in1,in2);
begin
    in1=in2
end

begin
    v2:='1';
    v2:=vv.cd;
    read(v1);
    v1:=v1+10;
    write(v1)
end
```
