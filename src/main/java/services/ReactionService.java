package services;

import java.util.ArrayList;
import java.util.List;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.ReactionConverter;
import actions.views.ReactionView;
import actions.views.ReportConverter;
import actions.views.ReportView;
import constants.JpaConst;
import models.Reaction;

public class ReactionService extends ServiceBase {

    /**
     * リアクションテーブルに登録する
     * @param rev 一旦中身なし
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> create(ReactionView rev) {
        createInternal(rev);
        return new ArrayList<String>();

    }

    /**
     * リアクションデータを1件登録する
     * @param rev 日報データ
     */
    private void createInternal(ReactionView rev) {

        em.getTransaction().begin();
        em.persist(ReactionConverter.toModel(rev));
        em.getTransaction().commit();

    }

    /**
     * 指定した日報データにリアクションデータがあるか　あり/なし判定
     * @param riaction
     * @return リアクションデータのあり/なし
     */
    public boolean countReaction(EmployeeView employee, ReportView report) {

        long count = (long) em.createNamedQuery(JpaConst.Q_REA_COUNT, Long.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .setParameter(JpaConst.JPQL_PARM_REPORT, ReportConverter.toModel(report))
                .getSingleResult();

        if (count == 0) {
            return false;

        } else {
            return true;
        }
    }

    public List<Reaction> getReaction(EmployeeView employee, ReportView report) {

        List<Reaction> list = em.createNamedQuery(JpaConst.Q_REA_DATA, Reaction.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .setParameter(JpaConst.JPQL_PARM_REPORT, ReportConverter.toModel(report))
                .getResultList();
        return list;
    }

    /**
     * リアクションデータを1件削除する
     * @param rev 日報データ
     */
    public void cancel(ReactionView rev) {
        System.out.println(rev.getId());

        em.getTransaction().begin();
        Reaction r = findOneInternal(rev.getId());
        em.remove(r); // データ削除
        em.getTransaction().commit();

    }

    public ReactionView findOne(int id) {
        return ReactionConverter.toView(findOneInternal(id));
    }

    /**
     * idを条件にデータを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    private Reaction findOneInternal(int id) {
        return em.find(Reaction.class, id);
    }
}