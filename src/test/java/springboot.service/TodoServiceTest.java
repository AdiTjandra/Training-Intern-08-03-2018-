package springboot.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TodoServiceTest {

    @InjectMocks
    private TodoService todoService;

    @Mock
    private TodoRepository todoRepository;

    @Before
    public void setUp() throws Exception {
//        this.todoRepository = new TodoRepository();
//        this.todoService = new TodoService(this.todoRepository);
        MockitoAnnotations.initMocks(this);


        //BDD<Mockito juga bisa di before
//        //Implementasi palsu jika dipanggil function store dengan parameter todo1, dia return true tanpa harus panggil function di TodoRepository
//        BDDMockito.given(this.todoRepository.store(new Todo("todo1", TodoPriority.MEDIUM))).willReturn(true);

//        //Sama dengan atas, untuk parameter  "wrong-data" dia return false
//        BDDMockito.given(this.todoRepository.store(new Todo("wrong-data", TodoPriority.MEDIUM))).willReturn(false);
    }

    @After
    public void tearDown() throws Exception {
        //Mastiin tidak ada interaksi lain yg tidak kepakai
        Mockito.verifyNoMoreInteractions(this.todoRepository);
    }

    @Test
    public void saveTodoSuccessTest() {
        //Implementasi palsu jika dipanggil function store dengan parameter todo1, dia return true tanpa harus panggil function di TodoRepository
        BDDMockito.given(this.todoRepository.store(new Todo("todo1", TodoPriority.MEDIUM))).willReturn(true);

        boolean result = this.todoService.saveTodo("todo1", TodoPriority.MEDIUM);
        Assert.assertThat(result, org.hamcrest.Matchers.equalTo(true));

        //Beri tau kalau store dengan param "todo1" itu diperlukan ke Mockito.verifyNoMoreInteractions(this.todoRepository); (line 41)
        Mockito.verify(this.todoRepository).store((new Todo("todo1", TodoPriority.MEDIUM)));
    }

    @Test
    public void saveTodoFailTest()
    {
        //Sama dengan atas, untuk parameter  "wrong-data" dia return false
        BDDMockito.given(this.todoRepository.store(new Todo("wrong-data", TodoPriority.MEDIUM))).willReturn(false);

        boolean result = this.todoService.saveTodo("wrong-data", TodoPriority.MEDIUM);
        Assert.assertThat(result, org.hamcrest.Matchers.equalTo(false));

        Mockito.verify(this.todoRepository).store((new Todo("wrong-data", TodoPriority.MEDIUM)));
    }

    @Test
    public void getAll() {
//        //Untuk tandain belum dibuat
//        Assert.fail();

        List<Todo> result = this.todoService.getAll();
        List<Todo> repoResult = new ArrayList<Todo>();
        Assert.assertThat(result, org.hamcrest.Matchers.equalTo(repoResult));

        //Beri tau kalau function getAll itu diperlukan ke Mockito.verifyNoMoreInteractions(this.todoRepository); (line 41)
        Mockito.verify(this.todoRepository).getAll();
    }

}