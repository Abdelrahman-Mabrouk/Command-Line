# مشروع مفسر سطر الأوامر (CLI)

## نظرة عامة
هذا المشروع ينفذ مفسر سطر أوامر (CLI) أساسي يشبه شل Unix/Linux باستخدام لغة Java. يدعم الأوامر الأساسية لإدارة الملفات والمجلدات والتنقل والعمليات المتقدمة.

## المميزات
- **أوامر إدارة الملفات والمجلدات**:
  - `mkdir`: إنشاء مجلد جديد.
  - `rmdir`: حذف مجلد فارغ.
  - `touch`: إنشاء ملف جديد.
  - `mv`: نقل أو إعادة تسمية ملف.
  - `rm`: حذف ملف.

- **أوامر التنقل**:
  - `pwd`: عرض المسار الحالي.
  - `cd`: التنقل بين المجلدات.
  - `ls`: عرض محتويات المجلد.
  - `ls -a`: عرض جميع المحتويات بما في ذلك الملفات المخفية.
  - `ls -r`: عرض المحتويات بترتيب عكسي.

- **أوامر متقدمة**:
  - `cat`: عرض محتويات ملف.
  - `>`: إعادة توجيه المخرجات إلى ملف (كتابة).
  - `>>`: إعادة توجيه المخرجات إلى ملف (إضافة).
  - `|`: ربط المخرجات من أمر إلى آخر.

- **أوامر داخلية**:
  - `exit`: إنهاء مفسر سطر الأوامر.
  - `help`: عرض قائمة بالأوامر المتاحة وكيفية استخدامها.

## التثبيت
1. قم بنسخ المستودع:
   ```bash
   git clone <repository-url>
   cd <repository-name>
