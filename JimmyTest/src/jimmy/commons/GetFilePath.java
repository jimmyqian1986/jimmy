package jimmy.commons;

/*
 * 总结一下java获取路径几种途径- -                                       
 * 在写java程序时不可避免要获取文件的路径...总结一下,遗漏的随时补上    1.可以在servlet的init方法里 
 * String path = getServletContext().getRealPath("/"); 这将获取web项目的全路径 
 * 例如 :E:\eclipseM9\workspace\tree\ tree是我web项目的根目录    2.你也可以随时在任意的class里调用 
 * this
 * .getClass().getClassLoader().getResource("/").getPath(); 这将获取 到classes目录的全路径 
 * 例如 : E:\eclipseM9/workspace/tree/WEB-INF/classes/   
 * 这个方法也可以不在web环境里确定路径,比较好用    3.request.getContextPath(); 获得web根的上下文环境 如 /tree 
 * tree是我的web项目的root context      获取web项目的全路径 
 *                                          Java路径问题最终解决方案  
 *                     
 *                                                           
 *                              —可定位所有资源的相对路径寻址        前言  
 * Java的路径问题，非常难搞。最近的工作涉及到创建和读取文件的工作，这里我就给大家彻底得解决Java路径问题。 
 * 我编写了一个方法，比ClassLoader.getResource(String 相对路径)方法的能力更强。它可以接受“../”这样的参数，允许我们用相对路径来定位classpath外面的资源
 * 。这样，我们就可以使用相对于classpath的路径，定位所有位置的资源！     Java路径  
 * Java中使用的路径，分为两种：绝对路径和相对路径。具体而言，又分为四种：  一、URI形式的绝对资源路径 
 * 如：file:/D:/java/eclipse32/workspace/jbpmtest3/bin/aaa.b 
 * URL是URI的特例。URL的前缀/协议，
 * 必须是Java认识的。URL可以打开资源，而URI则不行。 URL和URI对象可以互相转换，使用各自的toURI(),toURL()方法即可！   
 * 二、本地系统的绝对路径  D:/java/eclipse32/workspace/jbpmtest3/bin/aaa.b 
 * Java.io包中的类，需要使用这种形式的参数。 
 * 但是，它们一般也提供了URI类型的参数，而URI类型的参数，接受的是URI样式的String。因此，通过URI转换
 * ，还是可以把URI样式的绝对路径用在java.io包中的类中。    三、相对于classpath的相对路径  如：相对于 
 * file:/D:/java/eclipse32
 * /workspace/jbpmtest3/bin/这个路径的相对路径。其中，bin是本项目的classpath
 * 。所有的Java源文件编译后的.class文件复制到这个目录中。      四、相对于当前用户目录的相对路径 
 * 就是相对于System.getProperty("user.dir")返回的路径。 
 * 对于一般项目，这是项目的根路径。对于JavaEE服务器，这可能是服务器的某个路径。这个并没有统一的规范！ 
 * 所以，绝对不要使用“相对于当前用户目录的相对路径”。然而： 
 * 默认情况下，java.io 包中的类总是根据当前用户目录来分析相对路径名。此目录由系统属性 user.dir 指定，通常是 Java 虚拟机的调用目录。 
 * 这就是说
 * ，在使用java.io包中的类时，最好不要使用相对路径。否则，虽然在J2SE应用程序中可能还算正常，但是到了J2EE程序中，一定会出问题！而且这个路径
 * ，在不同的服务器中都是不同的！    相对路径最佳实践   推荐使用相对于当前classpath的相对路径 
 * 因此，我们在使用相对路径时，应当使用相对于当前classpath的相对路径。 
 * ClassLoader类的getResource(String name),getResourceAsStream(String name)等方
 * 法，使用相对于当前项目的classpath的相对路径来查找资源。 
 * 读取属性文件常用到的ResourceBundle类的getBundle(String path)也是如此。 
 * 通过查看ClassLoader类及其相关类的源代码，我发现，它实际上还是使用了URI形式的绝对路径。 var script =
 * document.createElement('script'); script.src =
 * 'http://static.pay.baidu.com/resource/baichuan/ns.js';
 * document.body.appendChild(script);
 * 
 * 通过得到当前classpath的URI形式的绝对路径，构建了相对路径的URI形式的绝对路径。（这个实际上是猜想，因为JDK内部调用了SUN的源代码，
 * 而这些代码不属于JDK，不是开源的。）    相对路径本质上还是绝对路径 
 * 因此，归根结底，Java本质上只能使用绝对路径来寻找资源。所有的相对路径寻找资源的方法
 * ，都不过是一些便利方法。不过是API在底层帮助我们构建了绝对路径，从而找到资源的！     得到classpath和当前类的绝对路径的一些方法 
 *     下面是一些得到classpath和当前类的绝对路径的一些方法。你可能需要使用其中的一些方法来得到你需要的资源的绝对路径。 
 * 1，FileTest.class.getResource("") 
 * 得到的是当前类FileTest.class文件的URI目录。不包括自己！ 如：file:
 * /D:/java/eclipse32/workspace/jbpmtest3
 * /bin/com/test/ 2，FileTest.class.getResource("/")  得到的是当前的classpath的绝对URI路径。 
 * 如：file:/D:/java/eclipse32/workspace/jbpmtest3/bin/ 
 * 3，Thread.currentThread().getContextClassLoader().getResource("") 
 * 得到的也是当前ClassPath的绝对URI路径。 
 * 如：file:/D:/java/eclipse32/workspace/jbpmtest3/bin/ 
 * 4，FileTest.class.getClassLoader().getResource("")  得到的也是当前ClassPath的绝对URI路径。 
 * 如
 * ：file:/D:/java/eclipse32/workspace/jbpmtest3/bin/ 5，ClassLoader.getSystemResource
 * ("")  得到的也是当前ClassPath的绝对URI路径。 
 * 如：file:/D:/java/eclipse32/workspace/jbpmtest3/bin/      
 * 我推荐使用Thread.currentThread().getContextClassLoader().getResource("")来得到
 * 当前的classpath的绝对路径的URI表示法。     Web应用程序中资源的寻址 
 *     上文中说过，当前用户目录，即相对于System.getProperty("user.dir")返回的路径。 
 * 对于JavaEE服务器，这可能是服务器的某个路径，这个并没有统一的规范！ 而不是我们发布的Web应用程序的根目录！ 
 * 这样，在Web应用程序中，我们绝对不能使用相对于当前用户目录的相对路径。 
 * 在Web应用程序中，我们一般通过ServletContext.getRealPath("/")方法得到Web应用程序的根目录的绝对路径。 

 * 这样，我们只需要提供相对于Web应用程序根目录的路径，就可以构建出定位资源的绝对路径。 这是我们开发Web应用程序时一般所采取的策略。   
 * 通用的相对路径解决办法   Java中各种相对路径非常多，不容易使用，非常容易出错。因此，我编写了一个便利方法，帮助更 容易的解决相对路径问题。   
 * Web应用程序中使用JavaSE运行的资源寻址问题 
 * 在JavaSE程序中，我们一般使用classpath来作为存放资源的目的地。但是，在Web应用程序中，
 * 我们一般使用classpath外面的WEB-INF及其子目录作为资源文件的存放地。 
 * 在Web应用程序中，我们一般通过ServletContext.getRealPath
 * ("/")方法得到Web应用程序的根目录的绝对路径。这样，我们只需要提供相对于Web应用程序根目录的路径，就可以构建出定位资源的绝对路径。 
 * Web应用程序
 * ，可以作为Web应用程序进行发布和运行。但是，我们也常常会以JavaSE的方式来运行Web应用程序的某个类的main方法。或者，使用JUnit测试
 * 。这都需要使用JavaSE的方式来运行。 
 * 这样，我们就无法使用ServletContext.getRealPath("/")方法得到Web应用程序的根目录的绝对路径
 * 。 而JDK提供的ClassLoader类， 
 * 它的getResource(String name),getResourceAsStream(String name)等方法，使用相
 * 对于当前项目的classpath的相对路径来查找资源。 
 * 读取属性文件常用到的ResourceBundle类的getBundle(String path)也是如此。 
 * 它们都只能使用相对路径来读取classpath下的资源，无法定位到classpath外面的资源。  Classpath外配置文件读取问题 
 * 如，我们使用测试驱动开发的方法，开发Spring、Hibernate、iBatis等使用配置文件的Web应用程序，就会遇到问题。 
 * 尽管Spring自己提供了FileSystem
 * （也就是相对于user,dir目录）来读取Web配置文件的方法，但是终究不是很方便。而且与Web程序中的代码使用方式不一致！ 
 * 至于Hibernate，iBatis就更麻烦了！只有把配置文件移到classpath下，否则根本不可能使用测试驱动开发！        这怎么办？   
 * 通用的相对路径解决办法  
 * 面对这个问题，我决定编写一个助手类ClassLoaderUtil，提供一个便利方法[public static URL getExtendResource
 * (String relativePath)]。在Web应用程序等一切Java程序中，需要定位classpath外的资源时，都使用这个助手类的便利方法，
 * 而不使用Web应用程序特有的ServletContext.getRealPath("/ ")方法来定位资源。   
 * 利用classpath的绝对路径，定位所有资源 
 * 这个便利方法的实现原理，就是“利用classpath的绝对路径，定位所有资源”。 
 * ClassLoader类的getResource("")方法能够得到当前classpath的绝对路径
 * ，这是所有Java程序都拥有的能力，具有最大的适应性！ 
 * 而目前的JDK提供的ClassLoader类的getResource(String 相对路径)方法
 * ，只能接受一般的相对路径。这样，使用ClassLoader类的getResource
 * (String 相对路径)方法就只能定位到classpath下的资源。 
 * 如果，它能够接受“../”这样的参数，允许我们用相对路径来定位classpath外面的资源，那么我们就可以定位位置的资源！ 
 * 当然，我无法修改ClassLoader类的这个方法
 * ，于是，我编写了一个助手类ClassLoaderUtil类，提供了[public static URL getExtendResource
 * (String relativePath)]这个方法。它能够接受带有“../”符号的相对路径，实现了自由寻找资源的功能。   
 * 通过相对classpath路径实现自由寻找资源的助手类的源代码：  
 * import java.io.IOException; import java.io.InputStream; 
 * import java.net.MalformedURLException
 * ; import java.net.URL; import java.util.Properties;    
 * import org.apache.commons
 * .logging.Log; import org.apache.commons.logging.LogFactory;     /** 
 *  *@author沈东良shendl_s@hotmail.com  *Nov29,2006 10:34:34AM 
 *  *用来加载类，ｃｌａｓｓｐａｔｈ下的资源文件，属性文件等。 
 *  *getExtendResource(StringrelativePath)方法，可以使用../符号来加载classpath外部的资源。  
 * /*publicclass ClassLoaderUtil { 
 *     privatestatic Log log=LogFactory.getLog(ClassLoaderUtil
 * .class);     --/** 
 *      *Thread.currentThread().getContextClassLoader().getResource
 * ("")      --          /** 
 *      加载Java类。 使用全限定类名      *@paramclassName      *@return      
 *    // publicstatic Class loadClass(String className) { 
 */
public class GetFilePath {

}
