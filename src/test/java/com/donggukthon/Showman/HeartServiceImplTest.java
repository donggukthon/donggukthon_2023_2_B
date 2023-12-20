package com.donggukthon.Showman;

import com.donggukthon.Showman.dto.heart.request.HeartLikeRequest;
import com.donggukthon.Showman.entity.Heart;
import com.donggukthon.Showman.entity.Posting;
import com.donggukthon.Showman.entity.User;
import com.donggukthon.Showman.repository.HeartRepository;
import com.donggukthon.Showman.repository.PostingRepository;
import com.donggukthon.Showman.repository.UserRepository;
import com.donggukthon.Showman.service.heart.HeartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class HeartServiceImplTest {

    @Autowired
    private HeartRepository heartRepository;

    @Autowired
    private HeartServiceImpl heartServiceImpl;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostingRepository postingRepository;

    @BeforeEach
    public void before() {
        User user = User.builder().userId(1L).nickname("test").build();
        Posting posting = Posting.builder().postingId(1L).user(user).build();
        userRepository.saveAndFlush(user);
        postingRepository.saveAndFlush(posting);
    }

//    @AfterEach
//    public void after() {
//        heartRepository.deleteAll();
//        postingRepository.deleteAll();
//        userRepository.deleteAll();
//    }

    @Test
    public void likeTest() {
        // given
        Posting posting = Posting.builder().postingId(1L).build();
        User user = User.builder().userId(1L).build();
        userRepository.saveAndFlush(user);
        postingRepository.saveAndFlush(posting);

        HeartLikeRequest heartLikeRequest = new HeartLikeRequest(1L);

        // when
        heartServiceImpl.like(heartLikeRequest);

        // then
        assert(heartRepository.findAll().size() == 1);
    }

    public  void createHeart(User user, Posting posting) {
        Heart heart = Heart.builder()
                .user(user)
                .posting(posting)
                .build();

        heartRepository.saveAndFlush(heart);
    }

    @Test
    @DisplayName("100번 동시에 좋아요")
    public void request_100_AtTheSameTime() throws InterruptedException {
        int treadCount = 100;

        User user = User.builder().userId(1L).nickname("test").build();
        Posting posting = Posting.builder().postingId(1L).user(user).build();
        userRepository.saveAndFlush(user);
        postingRepository.saveAndFlush(posting);

        //멀티스레드 이용 ExecutorService : 비동기를 단순하게 처리할 수 있또록 해주는 java api
        ExecutorService executorService = Executors.newFixedThreadPool(32);

        //다른 스레드에서 수행이 완료될 때 까지 대기할 수 있도록 도와주는 API - 요청이 끝날때 까지 기다림
        CountDownLatch latch = new CountDownLatch(treadCount);

        for (int i = 0; i < treadCount; i++) {
            executorService.submit(() -> {
                try {
                    createHeart(user, posting);
                }
                finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
//        Heart heart = heartRepository.findById(1L).orElseThrow();

//        @Query("select count(h) from Heart h")
        Long count = heartRepository.count();

        assertThat(count).isEqualTo(100L);

    }
}
