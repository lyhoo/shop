package com.example.lyhoo.shop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class Myhelper extends SQLiteOpenHelper {
    public Myhelper(Context context){
        super(context,"shop.db",null,2);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE person(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,name VARCHAR(20) NOT NULL,password VARCHAR(20) NOT NULL,address VARCHAR(100) DEFAULT 0,nickname VARCHAR(30),phone VARCHAR(11) DEFAULT 0,consignee VARCHAR(20) DEFAULT 0)");
        db.execSQL("CREATE TABLE good(id INTEGER PRIMARY KEY AUTOINCREMENT , pic INTEGER, name VARCHAR(30) NOT NULL, address VARCHAR(30),price DOUBLE NOT NULL, freight INTEGER NOT NULL DEFAULT 0,category VARCHAR(20), describe TEXT)");
        db.execSQL("CREATE TABLE shopcart(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , user INTEGER NOT NULL,  goodid INTEGER NOT NULL, goodnum INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE history(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,user INTEGER NOT NULL,goodid INTEGER NOT NULL,buytime TIMESTAMP NOT NULL DEFAULT (datetime('now','localtime')), goodnum INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE favourite(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,user INTEGER NOT NULL,goodid INTEGER NOT NULL)");
//        sql = "insert into good(name,address,price,freight) values('牙膏','湖南省湘潭市',19.9,0),('牙刷','湖南省长沙市',9.9,10)" +
//                ",('毛巾','湖北省武汉市',11,0),('茶杯','广西省南昌市',22,5)" +
//                ",('围巾','河南省郑州市',35,0),('泡面','北京市',25,0)" +
//                ",('啤酒','湖南省郴州市',19.9,0),('面条','陕西省西安市',10,0)" +
//                ",('外套','广东省广州市',299,0),('裤子','上海市',129,0)" +
//                ",('剪刀','天津市',5.2,0),('奶粉','河南省开封市',50.3,0)";
//        db.execSQL(sql);
        String sql;
        sql = "insert into good(pic,name,address,price,freight,category,describe) values("+R.drawable.good1+",'一体式简约背靠椅 原创','湖南湘潭',199,0,'椅子','    此椅子采用一体式设计，由优质木材一体打造而成，整体风格简约而不简单，符合人体工学设计，可以带给用户最优质的体验，同时对凳脚经专业处理，可避免在移动时产生噪音')";
        db.execSQL(sql);
        sql = "insert into good(pic,name,address,price,freight,category,describe) values("+R.drawable.good2+",'头戴式耳机 杜比音效','湖南长沙',129,0,'耳机','    采用新型材料制作而成，整体美观,质量轻盈佩戴舒适，减去了传统头戴式耳机繁琐的不必要的部分，带给用户最佳的体验，同时，该耳机采用国家认证的杜比音效，享受最纯正的音乐盛宴')";
        db.execSQL(sql);
        sql = "insert into good(pic,name,address,price,freight,category,describe) values("+R.drawable.good3+",'入耳式耳机 极致体验','河南郑州',88,0,'耳机','    采用简单的灰色，可以使用线控麦克风接听电话，也可以通过简单易用的控件播放音乐，随时随地无缝切换，尽享顺畅的通话与聆听体验，在简单的外表下采用精湛的声学设计，配以轴向对齐驱动单元，带来震撼的聆听体验')";
        db.execSQL(sql);
        sql = "insert into good(pic,name,address,price,freight,category,describe) values("+R.drawable.good4+",'新型简约便捷饮水机','湖南郴州',369,0,'饮水机','    此饮水机摆脱传统饮水机的外表，带来新颖时尚的造型，同时不用加水储水，新鲜热水说来就来，每杯水只烧一次，每滴水都鲜活，一台会自我清洁的管线机高温蒸汽，自我清洁')";
        db.execSQL(sql);
        sql = "insert into good(pic,name,address,price,freight,category,describe) values("+R.drawable.good5+",'创意家具 原创吊灯','陕西西安',159.9,0,'吊灯','    采用温暖的粉红配色，配以温和的黄色灯光，不刺眼，同时带给用户视觉上的双重体验，设计师独创的设计风格，让你的家别具一格，给你一个更温馨的家')";
        db.execSQL(sql);
        sql = "insert into good(pic,name,address,price,freight,category,describe) values("+R.drawable.good6+",'创新设计 家用多变造型吊灯','天津',299,0,'吊灯','    黑色酷炫的造型，摆脱单一造型的想法，赋予其多变的外表同时采用钢制材料，更使得其显得与众不同，其不仅仅是一个灯具的作用，也无疑是家中一个不可多得的装饰品')";
        db.execSQL(sql);
        sql = "insert into good(pic,name,address,price,freight,category,describe) values("+R.drawable.good7+",'100%原榨果汁 菠萝口味 12瓶/箱','云南昆明',10,0,'果汁','    原材料选自昆明优质果林，原产地与工厂一条龙服务，新鲜采摘水果2小时内榨成果汁，避免时间带来果汁口感上的不足100%原榨果汁，不含任何添加物，给你口感与健康两方面的极致体验')";
        db.execSQL(sql);
        sql = "insert into good(pic,name,address,price,freight,category,describe) values("+R.drawable.good8+",'国货精品 香皂独特香味','广东广州',8.8,0,'香皂','    滋润保湿含有四分之一的乳液，无皂基，亲肤不刺激，滋润保湿不假滑，温和保湿不紧绷，深层清洁不油腻，含天然乳木果精华，奢宠滋养肌肤，集优雅香草气息，放松身心，呈中性PH值，温和清洁不伤肤')";
        db.execSQL(sql);
        sql = "insert into good(pic,name,address,price,freight,category,describe) values("+R.drawable.good9+",'便携式多功能U型枕','湖南株洲',29.9,0,'U型枕','    泰国天然乳胶U枕，感受泰国7级乳胶原料带来的体验，人体工学全贴合，曲面贴合颈部，脸颊，后脑，在办公中，路途上给你最佳的体验，每时每刻，颈椎倍感舒适')";
        db.execSQL(sql);
        sql = "insert into good(pic,name,address,price,freight,category,describe) values("+R.drawable.good10+",'USB充电线 快充 闪充','河南开封',19.9,0,'充电线','    基于原装，媲美原装，芯片智能识别快充电压，安全高效采用45根纯铜芯，电流转化率高达99%，提速不虚电，升级SR长尾防断裂设计，超耐用，智能适配各类手机快充，30分钟充电75%')";
        db.execSQL(sql);
        sql = "insert into good(pic,name,address,price,freight,category,describe) values("+R.drawable.good11+",'设计师原创简洁台灯','河南洛阳',128,0,'台灯','    5.5瓦柔光照射，贴近自然光，只为更护眼，灯光色温4000K触摸四挡调光适合自己的亮度才更护眼，减蓝光，更护眼，减少不良光源对眼睛的伤害，无频闪，长时间用眼不再疲劳')";
        db.execSQL(sql);
        sql = "insert into good(pic,name,address,price,freight,category,describe) values("+R.drawable.good12+",'刀具组合 轻捷便利','北京',199,0,'刀具','    一套解决厨房斩切问题，高硬度不锈钢，手工开刃，锋利耐用，人体工学设计，刀柄防滑省力，久握不累，新材料刀座，底部通风，造型优美，整套刀具采用30Cr13不锈钢，具备高硬度，高耐磨性和高防锈性，保证刀具历久弥新')";
        db.execSQL(sql);
        sql = "insert into good(pic,name,address,price,freight,category,describe) values("+R.drawable.good13+",'高筋面粉 还原最真实的味道','重庆',38.8,0,'面粉','    自然洁白，麦香馥郁，不含漂白剂自然生态安全健康，劲度适中柔而不软，针对中式面点精心研发，均衡配比，劲度适中，不沾不黏，细腻松软，口感香甜，成形快，口感佳，省时省力，想吃就吃')";
        db.execSQL(sql);
        sql = "insert into good(pic,name,address,price,freight,category,describe) values("+R.drawable.good14+",'简约便捷 钢制热水壶','上海',60,0,'热水壶','    48小时长效保温，健康材质使用更放心，内胆采用微表面过滤技术，有效吸附水中杂质等有害物质，实现水质净化，为您饮水健康安全把关，无惧酸碱，随心畅饮，避免不锈钢内胆的氧化和重金属，健康饮用每一杯水')";
        db.execSQL(sql);
        sql = "insert into good(pic,name,address,price,freight,category,describe) values("+R.drawable.good15+",'舒适懒人椅 懒人必备','江西南昌',169,0,'椅子','    折叠懒人沙发椅，更大更舒适再也不用担心宿舍学习时的舒适问题，柔软透气告别冷板凳，休息好了才有充沛的精力去学习')";
        db.execSQL(sql);
        sql = "insert into good(pic,name,address,price,freight,category,describe) values("+R.drawable.good16+",'创意环形客厅吊灯','湖南岳阳',328,0,'吊灯','    整体采用木质螺旋结构，造型新颖独特，给您不一样的体验，采用温和的黄色灯光，让你感受明亮的同时不刺激眼睛，不仅仅是一个吊灯，也是家中不错的装饰物')";
        db.execSQL(sql);
        sql = "insert into good(pic,name,address,price,freight,category,describe) values("+R.drawable.good17+",'复古 唱片机','河南洛阳',888,0,'唱片机','    质感的轻奢之选，让黑胶音乐重回你的生活，蓝牙无线传输无损传输随心移动，全自动播放，一键播放，轻松简单，内置RIAA唱放，连接有源音箱个，高保真音质，双转速设置，配合唱片种类，不同音效乐趣')";
        db.execSQL(sql);
        sql = "insert into good(pic,name,address,price,freight,category,describe) values("+R.drawable.good18+",'吹风机 静音 强劲风力','湖北武汉',128,0,'吹风机','    快速干发，智能温控，呵护秀发，手持平衡，防止过热损伤呵护头发自然光泽，快速干发，方便造型，应用Air Amplifier气流倍增技术，使其喷射3倍强劲气流，配合负离子技术，帮助减少静电，造型后头发表面变得更加顺滑')";
        db.execSQL(sql);
        sql = "insert into good(pic,name,address,price,freight,category,describe) values("+R.drawable.good19+",'创意简洁垃圾桶','广东佛山',19.9,0,'垃圾桶','    简约简单设计，不再因为垃圾桶的造型而影响家中整体的美观外表没有多余的装饰，圆洞设计便于取拿蓝色更加醒目')";
        db.execSQL(sql);
        sql = "insert into good(pic,name,address,price,freight,category,describe) values("+R.drawable.good20+",'纯正蜂蜜 原生态','福建厦门',56,0,'蜂蜜','    专门养蜂基地，成熟蜂蜜波美度大于等于42度，自然成熟原蜜，气泡丰富，富含活性酶成分')";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVeresion){}
}