package models.validators;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import actions.views.ReportView;
import constants.MessageConst;

/**
 * 日報インスタンスに設定されている値のバリデーションを行うクラス
 */
public class ReportValidator {

    /**
     * 日報インスタンスの各項目についてバリデーションを行う
     * @param rv 日報インスタンス
     * @return エラーのリスト
     */
    public static List<String> validate(ReportView rv) {
        List<String> errors = new ArrayList<String>();

        //タイトルのチェック
        String titleError = validateTitle(rv.getTitle());
        if (!titleError.equals("")) {
            errors.add(titleError);
        }

        //内容のチェック
        String contentError = validateContent(rv.getContent());
        if (!contentError.equals("")) {
            errors.add(contentError);
        }

        //出勤時刻入力チェック
        String inTimeError = validateInTime(rv.getInTime());
        if (!inTimeError.equals("")) {
            errors.add(inTimeError);
        }

        //退勤時刻入力チェック
        String outTimeError = validateOutTime(rv.getOutTime());
        if (!outTimeError.equals("")) {
            errors.add(outTimeError);
        }

        //出勤時間と退勤時間が逆転していたら、エラー
        String inOutError = validateInOut(rv.getInTime(),rv.getOutTime());
        if (!inOutError.equals("")) {
            errors.add(inOutError);
        }

        return errors;
    }

    /**
     * タイトルに入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param title タイトル
     * @return エラーメッセージ
     */
    private static String validateTitle(String title) {
        if (title == null || title.equals("")) {
            return MessageConst.E_NOTITLE.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 内容に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param content 内容
     * @return エラーメッセージ
     */
    private static String validateContent(String content) {
        if (content == null || content.equals("")) {
            return MessageConst.E_NOCONTENT.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 出勤時刻/退勤時刻に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param content 内容
     * @return エラーメッセージ
     */
    @SuppressWarnings("unlikely-arg-type")
    private static String validateInTime(LocalDateTime inTime) {
        if (inTime == null || inTime.equals("")) {
            return MessageConst.E_NOINTIME.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    @SuppressWarnings("unlikely-arg-type")
    private static String validateOutTime(LocalDateTime outTime) {
        if (outTime == null || outTime.equals("")) {
            return MessageConst.E_NOOUTTIME.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    // //出勤時間と退勤時間が逆転していたら、エラー
    private static String validateInOut(LocalDateTime inTime, LocalDateTime outTime) {
        if (inTime.isAfter(outTime)) {
            return MessageConst.E_INOUT.getMessage();
        }

        //正しく入力されていれば空文字を返却
        return "";
    }
}
