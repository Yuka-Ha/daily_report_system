package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Reaction;

/**
 * 日報データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class ReactionConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param rev ReactionViewのインスタンス
     * @return Reactionのインスタンス
     */
    public static Reaction toModel(ReactionView rev) {
        return new Reaction(
                rev.getId(),
                EmployeeConverter.toModel(rev.getEmployee()),
                ReportConverter.toModel(rev.getReport()));
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param re Reactionのインスタンス
     * @return ReactionViewのインスタンス
     */
    public static ReactionView toView(Reaction re) {

        if (re == null) {
            return null;
        }

        return new ReactionView(
                re.getId(),
                EmployeeConverter.toView(re.getEmployee()),
                ReportConverter.toView(re.getReport()));
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<ReactionView> toViewList(List<Reaction> list) {
        List<ReactionView> revs = new ArrayList<>();

        for (Reaction re : list) {
            revs.add(toView(re));
        }

        return revs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param r DTOモデル(コピー先)
     * @param rv Viewモデル(コピー元)
     */
    public static void copyViewToModel(Reaction re, ReactionView rev) {
        re.setId(rev.getId());
        re.setEmployee(EmployeeConverter.toModel(rev.getEmployee()));

    }

}