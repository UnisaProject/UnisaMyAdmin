// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  production: false,
  exam_period_service_host_url: 'http://localhost:8080/examtimetable',
  exam_admission_service_host_url: 'http://localhost:8080/examtimetable',
  exam_period_date_service_host_url: 'http://localhost:8080/examtimetable'
};
