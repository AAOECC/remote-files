# 注解(Annotation)
* ## JDK内置注解(包含于java.lang):
   
  * @Override <br>
  重构
  
  * @Deprecate <br>
  已过时
  
  * @SuppressWarnings <br>
  压制警告
   

* ## 自定义注解
* ### 格式
  ```
  元注解
  public @interface AnnotationName{
    属性方法；
  }
  ```
* ### 本质：<br>
  注解本质是接口，继承自java.lang.annotation.Annotation类
  ```
  public interface AnnotationName extends java.lang.annotation.Annotation {}
  ```
* ### 属性：接口中可以定义的抽象方法 
  * #### 要求：
    1. 属性的返回值类型（只能从下列类型中取值）：
          * 基本类型
          * String 
          * enum枚举类型
          * 注解
          * 以上类型的数组
    2. 定义属性后，在使用时需要给属性赋值
          * 使用 default 关键字进行初始化，则不需要赋值
          * 只包含 value 关键字不需要标注
* ### 元注解： 描述注解的注解
    * @Target :  描述注解能够作用的位置 <br>
      属性值 ：ElementType枚举类型
    * @Retention : 描述注解被保留的阶段 <br>
      属性值 ： RetentionPolicy枚举类型
    * @Documation ： 描述注解是否被抽取的api文档中
    * @Inherited ： 描述注解是否被子类继承
* ### 在程序中使用（解析）注解: <br> <nbsp> <nbsp> <nbsp> -----获取注解中定义的属性值 <br>
  1. 获取注解定义位置的对象 （Class, Method, Field）
  2. 获取指定的注解
      ``` 
      getAnnotation();
      ```
  3. 调用注解中的抽象方法获取指定配置的属性值
