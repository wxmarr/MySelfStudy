package gl.com.greendaodemo;

/**
 * Created by mac on 15-10-28.
 * 这个java文件只是用来说明一些用法的，并不具备实际功能
 */
public class Usage {

    /**
     * 添加约束----->详情查看ExampleDaoGenerator.java中的代码
     */

    /**
     * 普通的CURL----->详情查看dao的所有方法
     */

//    schema.enableKeepSectionsByDefault();//通过次Schema对象添加的所有实体都不会覆盖自定义的代码
//    //或者
//    entity.setHasKeepSections(true);//单独让某个实体不覆盖自定义的代码

    /**
     * 多线程下使用GreenDao
     * 需要调用query的forCurrentThread()为当前的线程获取一个query对象。
     */

    /**
     * 多条件查询
     *  gt  大于 greater than
     *  ge  等于或大于  equal greater
     * 名字叫“乔”和(出生年份大于1970或(出生年份是1970年,出生月等于或大于10(10月)。
     *   QueryBuilder qb = userDao.queryBuilder();
         qb.where(Properties.FirstName.eq("Joe"),
         qb.or(Properties.YearOfBirth.gt(1970),
         qb.and(Properties.YearOfBirth.eq(1970), Properties.MonthOfBirth.ge(10))));
         List youngJoes = qb.list();

     //也可以这样
         Query query = userDao.queryBuilder().where(
         Properties.FirstName.eq("Joe"), Properties.YearOfBirth.eq(1970))
         .build();
         List joesOf1970 = query.list();
     // 同样等价与 1977年出生的Marias
         query.setParameter(0, "Maria");
         query.setParameter(1, 1977);
         List mariasOf1977 = query.list();
     */

    /**
     * 嵌套查询
        查询raw
     Query query = userDao.queryBuilder().where(
                        new StringCondition("_ID IN " +
                        "(SELECT USER_ID FROM USER_MESSAGE WHERE READ_FLAG = 0)").build();

     */

    /**
        使用连接查询  查询用户名为admin
     Query query = userDao.queryRawCreate(
             ", GROUP G WHERE G.NAME=? AND T.GROUP_ID=G._ID", "admin");
     */

    /**

     list() 所有的实体都被加载到内存中,结果是一个ArrayList，比较容易使用
     listLazy() 实体按照需求加载，并不会查询完立即加载进内存，只会在需要的时候加载，并且会缓存在一个list之中，并需调用close关闭
     listLazyUnCached() 一个虚拟的实体集，任何访问都必须从数据库中加载，必须被关闭
     listIterator() 让你便利加载，数据没有被缓存，必须关闭
     */

}
