package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class ExampleDaoGenerator {
    public static void main(String[] args) throws Exception{
        //自动生成的数据库版本号，和生成代码的包路径
        Schema schema = new Schema(2,"gl.com.greendaodemo");
//        当然，如果你愿意，你也可以分别指定生成的 Bean 与 DAO 类所在的目录，只要如下所示：
//      Schema schema = new Schema(1, "me.itangqi.bean");
//      schema.setDefaultJavaPackageDao("me.itangqi.dao");

        // 模式（Schema）同时也拥有两个默认的 flags，分别用来标示 entity 是否是 activie 以及是否使用 keep sections。
        // schema2.enableActiveEntitiesByDefault();
        // schema2.enableKeepSectionsByDefault();

        // 一旦你拥有了一个 Schema 对象后，你便可以使用它添加实体（Entities）了。
        schema.enableKeepSectionsByDefault();//通过次Schema对象添加的所有实体都不会覆盖自定义的代码

        addPerson(schema);
        addRelations(schema);
        // 最后我们将使用 DAOGenerator 类的 generateAll() 方法自动生成代码，此处你需要根据自己的情况更改输出目录（既之前创建的 java-gen)。
        // 其实，输出目录的路径可以在 build.gradle 中设置，有兴趣的朋友可以自行搜索，这里就不再详解。
        new DaoGenerator().generateAll(schema, "/Users/mac/Desktop/GLandroidstudy/AS/greendaodemo/src/main/java-gen");


    }

    public static void addPerson(Schema schema){
        Entity person = schema.addEntity("Person");
        //也可以重新给表命名
//        person.setTableName("Person_1");

        // greenDAO 会自动根据实体类的属性值来创建表字段，并赋予默认值
        // 接下来你便可以设置表中的字段：
        // 与在 Java 中使用驼峰命名法不同，默认数据库中的命名是使用大写和下划线来分割单词的。
        // For example, a property called “creationDate” will become a database column “CREATION_DATE”.
        //id自增 name->非空字符串 age-> int 非空
        person.addIdProperty();
        person.addStringProperty("name").notNull();
        person.addIntProperty("age").notNull();
        person.addStringProperty("card");
    }


    /**
     * relations hava 1:1 1:n m:n
     * 关系有一对一  一对多  多对多
     */

    public static void addRelations(Schema schema){
        /**
         * 人实体
         */
        Entity people = schema.addEntity("People");
        people.addStringProperty("name").primaryKey(); //名字
        people.addIntProperty("age"); //年龄
        /**
         * 身份证
         */
        Entity idcard = schema.addEntity("IdCard");
        idcard.addStringProperty("idcardnum").primaryKey(); //身份证号

        /**
         *  人和身份证 是一对一的关系
         */

        /** 一个人对应一个idcard **/
        Property propertyidcardnum = people.addStringProperty("idcardnum").getProperty();
        people.addToOne(idcard, propertyidcardnum);
        /**  一个idcrad 对应一个name ***/
        Property propertyname = idcard.addStringProperty("name").getProperty();
        idcard.addToOne(people, propertyname);

        /**
         *  人购物，订单
         */

        Entity order = schema.addEntity("Order");
        order.addIntProperty("orderid").primaryKey();
        order.addDoubleProperty("money").notNull();

        /**
         * 建立人与订单的一对多关系
         */
//        Property propertypeoplenum=people.addStringProperty("idcardnum").getProperty();
        Property property = order.addStringProperty("name1").getProperty();
        order.addToOne(people, property);
        people.addToMany(order,propertyname).setName("orders");

        /**
         * 课程
         */
        Entity course = schema.addEntity("Course");
        course.addStringProperty("courseid").primaryKey();
        course.addStringProperty("coursename").notNull();


        Property propertyPeopleId = course.addStringProperty("name").getProperty();
        course.addToMany(people,propertyPeopleId);

        Property propertyCourseID = people.addStringProperty("courseid").getProperty();
        people.addToMany(course,propertyCourseID);

    }
}
