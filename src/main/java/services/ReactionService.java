package services;

import java.util.ArrayList;
import java.util.List;

import actions.views.ReactionConverter;
import actions.views.ReactionView;

public class ReactionService extends ServiceBase {

    /**
     * 画面から入力された日報の登録内容を元にデータを1件作成し、日報テーブルに登録する
     * @param rv 日報の登録内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> create(ReactionView rev) {
        createInternal(rev);
        return new ArrayList<String>();

    }

    /**
     * 日報データを1件登録する
     * @param rv 日報データ
     */
    private void createInternal(ReactionView rev) {

        em.getTransaction().begin();
        em.persist(ReactionConverter.toModel(rev));
        em.getTransaction().commit();

    }
}