import { Directive, HostListener, ElementRef, OnInit } from "@angular/core";
import { CurrencyPipe } from "@angular/common";

@Directive({
  selector: "[unisa-currency-formatter]"
})
export class CurrencyFormatterDirective {

  private el: any;
  private PREFIX: string
  private DECIMAL_SEPARATOR: string;
  private THOUSANDS_SEPARATOR: string;
  private SUFFIX: string

  constructor(
    private elementRef: ElementRef,
//    private currencyPipe: MyCurrencyPipe,
    private currencyPipe: CurrencyPipe
  ) {
    this.el = this.elementRef.nativeElement;
    this.PREFIX = "";
    this.DECIMAL_SEPARATOR = ".";
    this.THOUSANDS_SEPARATOR = ",";
    this.SUFFIX = "";
  }

  ngOnInit() {
    this.el.value = this.currencyPipe.transform(this.el.value, null, '');
  }

  @HostListener("focus", ["$event.target.value"])
  onFocus(value) {
    this.el.value = this.parse(value);
  }

  @HostListener("blur", ["$event.target.value"])
  onBlur(value) {
    this.el.value = this.currencyPipe.transform(this.el.value, null, '');
  }

  //  opposite of transform
  parse(value: string, fractionSize: number = 2): string {
    let [integer, fraction = ""] = (value || "").replace(this.PREFIX, "")
      .replace(this.SUFFIX, "")
      .split(this.DECIMAL_SEPARATOR);

    integer = integer.replace(new RegExp(this.THOUSANDS_SEPARATOR, "img"), "");

    fraction = parseInt(fraction, 10) >= 0 && fractionSize >= 0
      ? this.DECIMAL_SEPARATOR + (fraction).substring(0, fractionSize)
      : "";

    return integer + fraction;
  }

  //
  // @HostListener("input", ["$event.target.value"])
  // onEvent(value) {
  //   this.el.value = this.parse(value);
  // }

}
