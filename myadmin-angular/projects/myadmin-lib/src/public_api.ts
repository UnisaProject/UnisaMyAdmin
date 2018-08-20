/*
 * Public API Surface of myadmin-lib
 */
// Services
import {StudyMaterialService} from "./lib/services/study-material.service";
export {ToasterNotificationService} from './lib/services/toaster-notification.service';
export {StudyMaterialService} from './lib/services/study-material.service';
export {StudentService} from './lib/services/student.service';

// Info objects
export {ErrorInfo} from './lib/info-objects/error-info';
export {DescriptionInfo} from './lib/info-objects/description-info';
export {StudentInfo} from './lib/info-objects/student-info';
export {ModuleEnrolmentInfo} from './lib/info-objects/module-enrolment-info';
export {StudyMaterialDetailInfo} from './lib/info-objects/study-material-detail-info';
export {ExamPaperMaterialInfo} from './lib/info-objects/exam-paper-material-info';

// Pipes
export {DescriptionPipe} from './lib/pipes/description.pipe';
export {ShortDescriptionPipe} from './lib/pipes/short-description.pipe';
export {OrderByPipe} from './lib/pipes/orderby.pipe';


export {unisaToasterConfig} from './lib/config/toaster-config';
export {HttpErrorInterceptor} from './lib/http-interceptors/http-error-interceptor';
export {MyadminLibModule} from './lib/myadmin-lib.module';
