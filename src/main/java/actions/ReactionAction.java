package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import actions.views.ReactionView;
import actions.views.ReportView;
import constants.AttributeConst;
import constants.ForwardConst;
import models.Reaction;
import services.ReactionService;
import services.ReportService;

/**
 * リアクション関する処理を行うActionクラス
 *
 */
public class ReactionAction extends ActionBase {

    private ReactionService reactionService;
    private ReportService reportService;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        reactionService = new ReactionService();
        reportService = new ReportService();

        //メソッドを実行
        invoke();
        reactionService.close();
        reportService.close();

    }

    /**
     * リアクション-いいね！をする
     * @throws ServletException
     * @throws IOException
     */
    public void reaction() throws ServletException, IOException {

        System.out.println("成功!!");

        //ログインしている従業員を取得する
        EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

        //idを条件に日報データを取得する
        ReportView rv = reportService.findOne(toNumber(getRequestParam(AttributeConst.REP_ID)));

        //パラメータの値をもとに日報情報のインスタンスを作成する
        ReactionView rev = new ReactionView(
                null,
                ev,
                rv);

        //日報情報登録
         reactionService.create(rev);
         System.out.println("登録成功!!");

         //詳細ページに戻る
         putRequestScope(AttributeConst.REPORT, rv); //取得した日報データ

         //詳細画面を表示
         forward(ForwardConst.FW_REP_SHOW);
    }


    /**
     * リアクション-いいね！をキャンセルする
     * @throws ServletException
     * @throws IOException
     */
    public void cancel() throws ServletException, IOException {

        System.out.println("成功!!");

        //ログインしている従業員を取得する
        EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

        //idを条件に日報データを取得する
        ReportView rv = reportService.findOne(toNumber(getRequestParam(AttributeConst.REP_ID)));

        System.out.println(rv.getId());
        //パラメータの値をもとに日報情報のインスタンスを作成する
        List<Reaction> list = reactionService.getReaction(ev, rv);

        for (Reaction r : list) {
            System.out.println(r.getId());
            ReactionView rev = reactionService.findOne(r.getId());
            reactionService.cancel(rev);
            System.out.println("削除成功!!");

        }

        //日報情報登録

         //詳細ページに戻る
         putRequestScope(AttributeConst.REPORT, rv); //取得した日報データ

         //詳細画面を表示
         forward(ForwardConst.FW_REP_SHOW);
    }
    /**
     * 一覧画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    /*  public void isReaction() throws ServletException, IOException {

        //対象の日報データを取得
        boolean isReaction = reactionService.countReaction();

        putRequestScope(AttributeConst.Reaction, reaction); //取得した日報データ
        putRequestScope(AttributeConst.REP_COUNT, reportsCount); //全ての日報データの件数
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        //一覧画面を表示
        forward(ForwardConst.FW_REP_INDEX);
    }

    */







}
