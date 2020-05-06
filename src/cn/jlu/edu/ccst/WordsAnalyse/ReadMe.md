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