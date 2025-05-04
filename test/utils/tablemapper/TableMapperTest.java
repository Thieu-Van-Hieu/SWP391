package utils.tablemapper;

import config.DBContext;
import course.dto.course.CourseRequestDTO;
import course.entity.MessageEntity;
import exception.utils.tablemapper.ObjectNotFoundException;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TableMapperTest {

    @Before
    public void beginTransaction() throws Exception {
        DBContext db = DBContext.getInstance();
        db.setDatabaseTest();
        TableMapper.beginTransaction();
    }
    
    @After
    public void rollbackTransaction() throws Exception {
        TableMapper.rollbackTransaction();
    }
    
    @Test
    public void testGet() throws Exception {
        MessageEntity messageEntity = TableMapper.get(MessageEntity.class, 1);

        MessageEntity messageEntityExpected = new MessageEntity(1, 1, 1, "Welcome to the course!", Timestamp.valueOf("2025-04-01 08:00:00.000"));

        Assert.assertEquals(messageEntityExpected, messageEntity);
    }

    @Test
    public void testGetAll() throws Exception {
        CourseRequestDTO courseRequestDTO = new CourseRequestDTO();
        courseRequestDTO.setCourseId(1);
        
        Field filterFields[] = new Field[1];
        filterFields[0] = CourseRequestDTO.class.getDeclaredField("courseId");

        ArrayList<MessageEntity> messageEntities = TableMapper.getAll(MessageEntity.class, filterFields, courseRequestDTO);

        ArrayList<MessageEntity> messageEntitesExpected = new ArrayList<>(List.of(
                new MessageEntity(1, 1, 1, "Welcome to the course!", Timestamp.valueOf("2025-04-01 08:00:00.000")),
                new MessageEntity(2, 1, 3, "Thank you!", Timestamp.valueOf("2025-04-01 08:05:00.000"))
        ));

        Assert.assertEquals(messageEntitesExpected, messageEntities);
    }
    
    @Test
    public void testCreate() throws Exception {
        MessageEntity messageEntityExpected = new MessageEntity();
        messageEntityExpected.setCourseId(1);
        messageEntityExpected.setMemberId(1);
        messageEntityExpected.setContent("Xin chao");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        messageEntityExpected.setSendAt(timestamp);
        
        int id = TableMapper.create(messageEntityExpected);
        messageEntityExpected.setMessageId(id);
        
        MessageEntity messageEntity = TableMapper.get(MessageEntity.class, id);
        Assert.assertEquals(messageEntityExpected, messageEntity);
    }
    
    @Test
    public void testUpdate() throws Exception {
        MessageEntity messageEntityExpected = new MessageEntity(2, 1, 3, "Xin chao", Timestamp.valueOf("2025-04-01 08:05:00.000"));
        TableMapper.update(messageEntityExpected);
        
        MessageEntity messageEntity = TableMapper.get(MessageEntity.class, 2);
        
        Assert.assertEquals(messageEntityExpected, messageEntity);
    }
    
    @Test
    public void testDelete() throws Exception {
        MessageEntity messageEntityExpected = new MessageEntity();
        messageEntityExpected.setMessageId(1);
        
        TableMapper.delete(messageEntityExpected);
        
        Assert.assertThrows(ObjectNotFoundException.class, () -> {
            TableMapper.get(MessageEntity.class, 1);
        });
    }
}
