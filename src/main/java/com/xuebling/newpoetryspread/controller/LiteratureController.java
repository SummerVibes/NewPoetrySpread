package com.xuebling.newpoetryspread.controller;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.WriteResult;
import com.mongodb.client.result.DeleteResult;
import com.xuebling.newpoetryspread.common.config.MongoConfig;
//import com.xuebling.newpoetryspread.dao.Impl.MongoRepositoryImpl;
import com.xuebling.newpoetryspread.dao.LibraryRepository;
import com.xuebling.newpoetryspread.dao.LiteratureRepository;
import com.xuebling.newpoetryspread.pojo.enums.ResponseMsg;
import com.xuebling.newpoetryspread.pojo.literaturelib.Literature;
import com.xuebling.newpoetryspread.pojo.literaturelib.literature.BasePlace;
import com.xuebling.newpoetryspread.pojo.literaturelib.literature.BaseTime;
import com.xuebling.newpoetryspread.pojo.literaturelib.literature.BaseType;
import com.xuebling.newpoetryspread.pojo.result.Response;
import com.xuebling.newpoetryspread.service.LiteratureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping("/lite")
public class LiteratureController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LiteratureRepository literatureRepository;
    @Autowired
    private LiteratureService literatureService;

    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    private LibraryRepository libraryRepository;
    @Autowired
    private HttpServletRequest request;



    private HttpServletResponse response;


    @GetMapping(path = "test")
    public Object test(){


//        ArrayList<String> list = new ArrayList<>();
//        list.add("5bfd5b5556bc3b2e483950d9");
//        list.add("5bfd9bc156bc3b2eb48ff69b");
//        list.add("5bfdac7256bc3b24c0cd33b8");
        /**插入**/
//        if(!libraryRepository.insertEmbedDoc(list,new Library())){
//            return new Response(ResponseMsg.TARGETNULL);
//        }
        /**删除**/
//        if (!libraryRepository.deleteEmbedDoc(list)){
//            return new Response(ResponseMsg.TARGETNULL);
//        }
        /**更新**/
//        libraryRepository.updateEmbedDoc(list,"repoName","ccc");
        /**新建**/
//        Library lib = new Library();
//        lib.setRepoName("xxx");
//        Library lib1 = new Library();
//        lib1.setRepoName("yyy");
//        Library lib2 = new Library();
//        lib2.setRepoName("zzz");
//        lib1.addSubRepo(lib2);
//        lib.addSubRepo(lib1);
//        libraryRepository.save(lib);
        /**不管**/
//        Optional<Library> library = libraryRepository.findById("5bfbae7e56bc3b2f900e5bbc");
        //对文献进行操作前需要先设置集合
        MongoConfig.setCollectionName("Literature");
        Object result = literatureRepository.findById("1").get();
        return new Response(ResponseMsg.SUCCESS);
    }

    @PostMapping(path = "test1")
    public Object  test1(@RequestBody Literature literature){

        String id = literature.getId();
        String state = literature.getState();
        String title = literature.getTitle();

        System.out.println(id);
        System.out.println(state);
        System.out.println(title);

        MongoConfig.setCollectionName("Literature");

        Literature s = new Literature();



        s.setId(id);
        s.setMediaType("txt");
        s.setState(state);
        BaseType bt = new BaseType();
        bt.setCategory("类型");
        bt.setType("类型");
        s.setStudyType(bt);
        s.setDocType(bt);
        s.setTranslated(true);
        s.setTitle(title);

        s.setLanguage("zn");

        ArrayList<String> newlist = new ArrayList<String>();

        newlist.add("cq");
        newlist.add("te");

        s.setAuthors(newlist);
        s.setEditors(newlist);
        s.setKeywords(newlist);

        s.setWholeText("2");
        s.setSubject("2");
        s.setDigest("2");
        s.setSource("2");

        BasePlace bp = new BasePlace();
        bp.setBeforePlace("1");
        bp.setNowPlace("1");

        s.setCreatePlace(bp);
        s.setPublishPlace(bp);

        BaseTime btime = new BaseTime();
        btime.setGregorian("2");
        btime.setMinguo("2");
        s.setCreateTime(btime);
        s.setPublishTime(btime);

        s.setFiles(newlist);

        s.setLanguage("eg");

//        try {
            literatureRepository.save(s);

        Object result = literatureRepository.save(new Literature());
        return new Response(ResponseMsg.SUCCESS);

//        }catch (Exception e){
////            System.out.println("false");
//        }


//        Object result = literatureRepository.save(new Literature());

//        Literature test = new Literature();
//        test.setId("1");
//
//        literatureRepository.save(test);


    }

        @PostMapping(path = "testjson")
        public Object  test2(@RequestBody JSONObject jsonParam){
            System.out.println(jsonParam.toJSONString());

    //         mongoTemplate.save(jsonParam,"test");

            Query query = new Query(Criteria.where("_id").is("5c162cfe3281d941c89fc02e"));
            DeleteResult result = mongoTemplate.remove(query, "test");

    //        Query query1 = new Query(Criteria.where("_id").is(1));
    //        DeleteResult result1 = mongoTemplate.remove(query1, "test");
    //
    //        result1.
    //        Query query2 = new Query(Criteria.where("_id").is(2));
    //        DeleteResult result2 = mongoTemplate.remove(query2, "test");



            return new Response(ResponseMsg.SUCCESS);



        }
    //删除文献，修改文献状态为delete
    @DeleteMapping(path = "/delete")
    public Object DeleteLite(@RequestBody Literature literature, BindingResult bindingResult) {
        //不应该这么写，写完那个回来改
        boolean ControllerState = literatureService.DeleteLiterature(literature);

            if (ControllerState){
                return new Response(ResponseMsg.SUCCESS);
            }else{
                return new Response(ResponseMsg.FAIL);
            }
    }

    //修改文献，更新文献对象所有属性
    @PostMapping(path = "/change")
    public Object modifyLiterature(HttpServletRequest request, @RequestBody JSONObject object) {

        Literature literature = literatureService.Exchange(object);

        return literatureService.ChangeLiterature(literature);
    }

}
