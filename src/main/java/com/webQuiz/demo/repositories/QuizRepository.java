package com.webQuiz.demo.repositories;

import com.webQuiz.demo.models.Quiz;
import com.webQuiz.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByAuthor(User user);
}
