package com.fastcampus.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TxService {
    @Autowired
    A1Dao a1Dao;
    @Autowired
    B1Dao b1Dao;

    public  void insertA1WithoutTx()throws Exception {
        a1Dao.insert(1, 100);
        a1Dao.insert(1, 200);
    }

   @Transactional(rollbackFor = Exception.class)//이 부분이 있어야 데이터가 하나만 들어가지않고 하나가 들어간 후 롤백이 실행된다.
  // @Transactional //runtimeException이나 error 만 롤백을 하기대문에 위의 구문을 써야한다.
    public  void insertA1WithFail()throws Exception {
        a1Dao.insert(1, 100);
      //  throw new RuntimeException(); //이 부분은 롤백을 하고
      //  throw new Exception();//이 구문은 롤백을 하지않는다 . tx어노테이션은 위의 2가지만 롤백을 하기때문이다.
        a1Dao.insert(1, 200);
    }
    @Transactional
    public  void insertA1WithSuccess()throws Exception {
        a1Dao.insert(1, 100);
        a1Dao.insert(2, 200);
    }

}
