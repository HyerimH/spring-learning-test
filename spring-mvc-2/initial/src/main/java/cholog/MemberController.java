package cholog;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MemberController {

    private List<Member> members = new ArrayList<>();
    private AtomicLong index = new AtomicLong(1);  // 인덱스 값 자동 증가

    // 멤버 생성
    @PostMapping("/members")
    public ResponseEntity<Void> create(@RequestBody Member member) {
        // ID를 자동 증가시키며 새로운 멤버 생성
        Member newMember = Member.toEntity(member, index.getAndIncrement());
        members.add(newMember);
        // 생성된 멤버의 URI 반환
        return ResponseEntity.created(URI.create("/members/" + newMember.getId())).build();
    }

    // 모든 멤버 조회
    @GetMapping("/members")
    public ResponseEntity<List<Member>> read() {
        return ResponseEntity.ok().body(members);
    }

    // 특정 멤버 수정
    @PutMapping("/members/{id}")
    public ResponseEntity<Void> update(@RequestBody Member newMember, @PathVariable Long id) {
        // ID로 멤버 찾기
        Member member = members.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Member not found"));

        // 멤버 정보 수정
        member.update(newMember);
        return ResponseEntity.ok().build();
    }

    // 특정 멤버 삭제
    @DeleteMapping("/members/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // ID로 멤버 찾기, 없으면 예외 발생
        Member member = members.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Member not found"));

        // 멤버 삭제
        members.remove(member);
        return ResponseEntity.noContent().build();
    }
}
