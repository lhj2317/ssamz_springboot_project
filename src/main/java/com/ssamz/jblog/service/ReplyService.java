package com.ssamz.jblog.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssamz.jblog.domain.Post;
import com.ssamz.jblog.domain.Reply;
import com.ssamz.jblog.domain.User;
import com.ssamz.jblog.repository.PostRepository;
import com.ssamz.jblog.repository.ReplyRepository;

@Service
public class ReplyService {
	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Transactional
	public void deleteReply(int replyId) {
		replyRepository.deleteById(replyId);
	}
	
//	@Transactional
//	public void updateReply(Reply Reply) {
//		Reply findReply = ReplyRepository.findById(Reply.getId()).get();
//		findReply.setTitle(Reply.getTitle());
//		findReply.setContent(Reply.getContent());
//	}
//	
//	@Transactional(readOnly = true)
//	public Reply getReply(int id) {
//		return ReplyRepository.findById(id).get();
//	}
//	
//	@Transactional(readOnly = true)
//	public Page<Reply> getReplyList(Pageable pageable) {
//		return ReplyRepository.findAll(pageable);
//	}
	
	@Transactional
	public void insertReply(int postId, Reply reply, User user) {
		Post post = postRepository.findById(postId).get();
		reply.setUser(user);
		reply.setPost(post);
		replyRepository.save(reply);
	}
}
