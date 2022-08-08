package red.kalos.core.entity;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * @Author: carl0
 * @DATE: 2022/8/7 17:11
 */
@Data
public class ComplexTop implements Comparable<ComplexTop>{
    String name;
    int score;

    @Override
    public int compareTo(@NotNull ComplexTop o) {
        int result;
        result = this.score-o.getScore();
        return result;
    }
}
