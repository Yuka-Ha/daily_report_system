package actions;

import java.io.IOException;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import actions.views.ReactionView;
import actions.views.ReportView;
import constants.AttributeConst;
import constants.ForwardConst;
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
}
